package com.and.movies.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseView> {
    @NonNull
    private final WeakReference<V> mView;

    public BasePresenter(@NonNull final V view) {
        mView = new WeakReference<>(view);
    }

    @UiThread
    public void init() {
    }

    @UiThread
    public void clear() {
        mView.clear();
    }

    protected void performViewAction(@NonNull final ViewAction<V> viewAction) {
        if (mView.get() != null) {
            viewAction.run(mView.get());
        }
    }
}
