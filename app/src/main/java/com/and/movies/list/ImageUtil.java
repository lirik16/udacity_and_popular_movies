package com.and.movies.list;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;

public class ImageUtil {
    //TODO: get available poster size and image base url by /configure request
    private static final int POSTER_WIDTH_SIZES[] = new int[] {92, 154, 185, 342, 500, 780};

    public static Uri createImageUriForView(@NonNull final View view,
                                        @NonNull final String imagePath) {
        int lowerBoundIndex = ArraysUtil.lowerBound(POSTER_WIDTH_SIZES, view.getWidth());
        if (lowerBoundIndex == POSTER_WIDTH_SIZES.length) {
            lowerBoundIndex = POSTER_WIDTH_SIZES.length - 1;
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https").
                authority("image.tmdb.org")
                .appendPath("t")
                .appendPath("p")
                .appendPath("w" + Integer.toString(POSTER_WIDTH_SIZES[lowerBoundIndex]))
                .appendEncodedPath(imagePath);
        return builder.build();
    }

}
