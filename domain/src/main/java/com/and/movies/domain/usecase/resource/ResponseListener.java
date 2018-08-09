package com.and.movies.domain.usecase.resource;

import android.support.annotation.NonNull;

public interface ResponseListener<Response> {
    void onResponse(@NonNull final Resource<Response> resource);
}
