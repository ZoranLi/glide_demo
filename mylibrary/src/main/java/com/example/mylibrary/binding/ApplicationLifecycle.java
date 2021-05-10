package com.example.mylibrary.binding;

import androidx.annotation.NonNull;

public class ApplicationLifecycle implements Lifecycle {
    @Override
    public void addListener(@NonNull LifecycleListener listener) {
        listener.onStart();
    }

    @Override
    public void removeListener(@NonNull LifecycleListener listener) {
        // Do nothing.
    }
}
