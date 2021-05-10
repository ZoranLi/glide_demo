package com.example.mylibrary.binding;

import androidx.annotation.NonNull;

import com.example.mylibrary.utils.Util;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

//非application作用域 监听fragment
public class ActivityFragmentLifecycle implements Lifecycle {
    private final Set<LifecycleListener> lifecycleListeners =
            Collections.newSetFromMap(new WeakHashMap<LifecycleListener, Boolean>());

    private boolean isStarted;//启动
    private boolean isDestroyed;//销毁

    @Override
    public void addListener(@NonNull LifecycleListener listener) {
        lifecycleListeners.add(listener);
        if (isDestroyed) {
            listener.onDestroy();
        } else if (isStarted) {
            listener.onStart();
        } else {
            listener.onStop();//首次启动
        }
    }

    @Override
    public void removeListener(@NonNull LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }

    public void onStart() {
        isStarted = true;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStart();
        }
    }

    public  void onStop() {
        isStarted = false;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onStop();
        }
    }

    public  void onDestroy() {
        isDestroyed = true;
        for (LifecycleListener lifecycleListener : lifecycleListeners) {
            lifecycleListener.onDestroy();
        }
    }
}
