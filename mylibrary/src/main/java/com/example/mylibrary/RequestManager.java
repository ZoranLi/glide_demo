package com.example.mylibrary;

import android.content.Context;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;

import com.example.mylibrary.binding.ApplicationLifecycle;
import com.example.mylibrary.binding.Lifecycle;
import com.example.mylibrary.binding.LifecycleListener;

public class RequestManager implements LifecycleListener {
    private final Lifecycle lifecycle;

    public RequestManager(Glide glide, Lifecycle lifecycle, Context applicationContext) {
        this.lifecycle = lifecycle;
        this.lifecycle.addListener(this);
    }


    @Override
    public void onStart() {
        //开始执行

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        this.lifecycle.removeListener(this);
    }



    /* *//**
     * 网络重连功能
     *//*
    private class RequestManagerConnectivityListener
            implements ConnectivityMonitor.ConnectivityListener {
        @GuardedBy("RequestManager.this")
        private final RequestTracker requestTracker;

        RequestManagerConnectivityListener(@NonNull RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        @Override
        public void onConnectivityChanged(boolean isConnected) {
            if (isConnected) {
                synchronized (RequestManager.this) {
                    requestTracker.restartRequests();
                }
            }
        }
    }*/
}
