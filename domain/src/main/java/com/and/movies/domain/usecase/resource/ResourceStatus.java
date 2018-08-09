package com.and.movies.domain.usecase.resource;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({ResourceStatus.IN_PROGRESS, ResourceStatus.SUCCEED, ResourceStatus.FAILED})
public @interface ResourceStatus {
    int IN_PROGRESS = 0;
    int SUCCEED = 1;
    int FAILED = 2;
}
