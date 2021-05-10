package com.example.mylibrary.binding;

import androidx.annotation.NonNull;

public interface Lifecycle {
    void addListener(@NonNull LifecycleListener var1);

    void removeListener(@NonNull LifecycleListener var1);
}
