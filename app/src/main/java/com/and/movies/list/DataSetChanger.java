package com.and.movies.list;

import android.support.annotation.NonNull;

public interface DataSetChanger<T> {
    void changeDataSet(@NonNull final T dataSet);
}
