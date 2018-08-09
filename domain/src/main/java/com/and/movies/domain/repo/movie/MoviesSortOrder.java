package com.and.movies.domain.repo.movie;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@IntDef({MoviesSortOrder.MOST_POPULAR, MoviesSortOrder.HIGHEST_RATED})
public @interface MoviesSortOrder {
    int MOST_POPULAR = 0;
    int HIGHEST_RATED = 1;

}
