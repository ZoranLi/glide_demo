package com.example.mylibrary;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentManager;

import com.example.mylibrary.binding.ActivityFragmentLifecycle;

import java.util.HashSet;
import java.util.Set;

/**
 * Android
 */
public class RequestManagerFragment extends Fragment {
    private static final String TAG = "SupportFragment";
    private final ActivityFragmentLifecycle lifecycle;
    @Nullable
    private RequestManager requestManager;

    private final Set<SupportRequestManagerFragment> childRequestManagerFragments = new HashSet<>();

    @Nullable
    private SupportRequestManagerFragment rootRequestManagerFragment;

    @Nullable
    private androidx.fragment.app.Fragment parentFragmentHint;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    @VisibleForTesting
    @SuppressLint("ValidFragment")
    public RequestManagerFragment(@NonNull ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public void setRequestManager(@Nullable RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    @NonNull
    ActivityFragmentLifecycle getGlideLifecycle() {
        return lifecycle;
    }

    @Nullable
    public RequestManager getRequestManager() {
        return requestManager;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

//    FragmentManager rootFragmentManager = getRootFragmentManager(this);
//    if (rootFragmentManager == null) {
//      if (Log.isLoggable(TAG, Log.WARN)) {
//        // Not expected to occur; ancestor fragments should be attached before descendants.
//        Log.w(TAG, "Unable to register fragment with root, ancestor detached");
//      }
//      return;
//    }
//
//    try {
//      registerFragmentWithRoot(getContext(), rootFragmentManager);
//    } catch (IllegalStateException e) {
//      // OnAttach can be called after the activity is destroyed, see #497.
//      if (Log.isLoggable(TAG, Log.WARN)) {
//        Log.w(TAG, "Unable to register fragment with root", e);
//      }
//    }
    }


    private void addChildRequestManagerFragment(SupportRequestManagerFragment child) {
        childRequestManagerFragments.add(child);
    }

    private void removeChildRequestManagerFragment(SupportRequestManagerFragment child) {
        childRequestManagerFragments.remove(child);
    }


    /**
     * Sets a hint for which fragment is our parent which allows the fragment to return correct
     * information about its parents before pending fragment transactions have been executed.
     */
    void setParentFragmentHint(@Nullable androidx.fragment.app.Fragment parentFragmentHint) {
        this.parentFragmentHint = parentFragmentHint;
        if (parentFragmentHint == null || parentFragmentHint.getContext() == null) {
            return;
        }
        FragmentManager rootFragmentManager = getRootFragmentManager(parentFragmentHint);
        if (rootFragmentManager == null) {
            return;
        }
//        registerFragmentWithRoot(parentFragmentHint.getContext(), rootFragmentManager);
    }

    @Nullable
    private static FragmentManager getRootFragmentManager(@NonNull androidx.fragment.app.Fragment fragment) {
        while (fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        return fragment.getFragmentManager();
    }



//    private void registerFragmentWithRoot(
//            @NonNull Context context, @NonNull FragmentManager fragmentManager) {
//        unregisterFragmentWithRoot();
//        rootRequestManagerFragment =
//                Glide.get(context)
//                        .getRequestManagerRetriever()
//                        .getSupportRequestManagerFragment(context, fragmentManager);
//        if (!equals(rootRequestManagerFragment)) {
//            rootRequestManagerFragment.addChildRequestManagerFragment(this);
//        }
//    }

//    private void unregisterFragmentWithRoot() {
//        if (rootRequestManagerFragment != null) {
//            rootRequestManagerFragment.removeChildRequestManagerFragment(this);
//            rootRequestManagerFragment = null;
//        }
//    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }

}
