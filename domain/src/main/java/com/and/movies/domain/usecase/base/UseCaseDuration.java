package com.and.movies.domain.usecase.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({UseCaseDuration.SHORT_RUNNING, UseCaseDuration.LONG_RUNNING})
public @interface UseCaseDuration {
    //like small computations or disk reading
    int SHORT_RUNNING = 0;

    //like network request
    int LONG_RUNNING = 1;
}
