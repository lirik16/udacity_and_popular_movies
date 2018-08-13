package com.and.movies.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

public interface ViewAction<V extends BaseView> {
    @UiThread
    void run(@NonNull final V view);
}
