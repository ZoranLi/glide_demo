package com.example.mylibrary;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.mylibrary.binding.ApplicationLifecycle;
import com.example.mylibrary.utils.Util;

import java.util.HashMap;
import java.util.Map;

public class RequestManagerRetriever implements Handler.Callback {
    @VisibleForTesting
    static final String FRAGMENT_TAG = "com.bumptech.glide.manager";

    @VisibleForTesting
    final Map<FragmentManager, SupportRequestManagerFragment> pendingSupportRequestManagerFragments =
            new HashMap<>();

    @SuppressWarnings("deprecation")
    @VisibleForTesting
    final Map<android.app.FragmentManager, RequestManagerFragment> pendingRequestManagerFragments =
            new HashMap<>();

    private RequestManager applicationManager;


    private static final int ID_REMOVE_FRAGMENT_MANAGER = 1;
    private static final int ID_REMOVE_SUPPORT_FRAGMENT_MANAGER = 2;

    private final Handler handler;

    public RequestManagerRetriever() {
        this.handler = new Handler(Looper.getMainLooper(), this /* Callback */);
    }

    /**
     * application 区域
     *
     * @param context
     * @return
     */
    @NonNull
    private RequestManager getApplicationManager(@NonNull Context context) {
        if (applicationManager == null) {
            synchronized (this) {
                if (applicationManager == null) {
                    Glide glide = Glide.get(context.getApplicationContext());
                    applicationManager = new RequestManager(glide, new ApplicationLifecycle(), context.getApplicationContext());
                }
            }
        }
        return applicationManager;
    }

    /**
     * 公共领域的application
     *
     * @param context
     * @return
     */
    @NonNull
    public RequestManager get(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper && ((ContextWrapper) context).getBaseContext().getApplicationContext() != null) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }
        // 红色区域
        return getApplicationManager(context);
    }

    @NonNull
    public RequestManager get(@NonNull FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            Util.assertNotDestroyed(activity);
            FragmentManager fm = activity.getSupportFragmentManager();
            return supportFragmentGet(activity, fm, /*parentHint=*/ null);
        }
    }

    @NonNull
    public RequestManager get(@NonNull Fragment fragment) {
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getContext().getApplicationContext());
        } else {
            FragmentManager fm = fragment.getChildFragmentManager();
            return supportFragmentGet(fragment.getContext(), fm, fragment);
        }
    }


    @NonNull
    private RequestManager supportFragmentGet(
            @NonNull Context context,
            @NonNull FragmentManager fm,
            @Nullable Fragment parentHint) {
        SupportRequestManagerFragment current =
                getSupportRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {//一个
            Glide glide = Glide.get(context);
//            requestManager =
//                    factory.build(glide, current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
            requestManager = new RequestManager(glide, current.getGlideLifecycle(), context);
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }


    @NonNull
    private SupportRequestManagerFragment getSupportRequestManagerFragment(
            @NonNull final FragmentManager fm, @Nullable Fragment parentHint) {
        SupportRequestManagerFragment current =
                (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingSupportRequestManagerFragments.get(fm);
            if (current == null) {
                current = new SupportRequestManagerFragment();
                current.setParentFragmentHint(parentHint);
                pendingSupportRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }

    @Override
    public boolean handleMessage(Message message) {
        boolean handled = true;
        Object removed = null;
        Object key = null;
        switch (message.what) {
            case ID_REMOVE_FRAGMENT_MANAGER:
                android.app.FragmentManager fm = (android.app.FragmentManager) message.obj;
                removed = pendingRequestManagerFragments.remove(fm);
                break;
            case ID_REMOVE_SUPPORT_FRAGMENT_MANAGER:
                FragmentManager supportFm = (FragmentManager) message.obj;
                key = supportFm;
                removed = pendingSupportRequestManagerFragments.remove(supportFm);
                break;
            default:
                handled = false;
                break;
        }
//        if (handled && removed == null && Log.isLoggable(TAG, Log.WARN)) {
//            Log.w(TAG, "Failed to remove expected request manager fragment, manager: " + key);
//        }
        return handled;
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @NonNull
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public RequestManager get(@NonNull android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException(
                    "You cannot start a load on a fragment before it is attached");
        }
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            android.app.FragmentManager fm = fragment.getChildFragmentManager();
            return fragmentGet(fragment.getActivity(), fm, fragment);
        }
    }


    @SuppressWarnings("deprecation")
    @NonNull
    public RequestManager get(@NonNull Activity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            Util.assertNotDestroyed(activity);
            android.app.FragmentManager fm = activity.getFragmentManager();
            return fragmentGet(activity, fm, /*parentHint=*/ null);
        }
    }


    @SuppressWarnings({"deprecation", "DeprecatedIsStillUsed"})
    @Deprecated
    @NonNull
    private RequestManager fragmentGet(
            @NonNull Context context,
            @NonNull android.app.FragmentManager fm,
            @Nullable android.app.Fragment parentHint) {
        RequestManagerFragment current = getRequestManagerFragment(fm, parentHint);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            // TODO(b/27524013): Factor out this Glide.get() call.
            Glide glide = Glide.get(context);
//            requestManager =
//                    factory.build(
//                            glide, current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
            requestManager = new RequestManager(glide,current.getGlideLifecycle(),context);
            current.setRequestManager(requestManager);
        }
        return requestManager;
    }

    @SuppressWarnings("deprecation")
    @NonNull
    private RequestManagerFragment getRequestManagerFragment(
            @NonNull final android.app.FragmentManager fm,
            @Nullable android.app.Fragment parentHint) {
        RequestManagerFragment current = (RequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
        if (current == null) {
            current = pendingRequestManagerFragments.get(fm);
            if (current == null) {
                current = new RequestManagerFragment();
//                current.setParentFragmentHint(parentHint);
                pendingRequestManagerFragments.put(fm, current);
                fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
                handler.obtainMessage(ID_REMOVE_FRAGMENT_MANAGER, fm).sendToTarget();
            }
        }
        return current;
    }
}
