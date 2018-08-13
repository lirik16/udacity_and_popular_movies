package com.and.movies.presenter.base;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

public interface BaseView<VS extends ViewState> {
    @UiThread
    void setViewState(@NonNull final VS viewState);
}
