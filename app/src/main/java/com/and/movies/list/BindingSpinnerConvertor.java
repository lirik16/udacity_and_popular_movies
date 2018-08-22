package com.and.movies.list;

import com.and.movies.domain.repo.movie.MoviesSortOrder;

public final class BindingSpinnerConvertor {
    private static final int MOST_POPULAR_SPINNER_POSITION = 0;
    private static final int HIGHEST_RATED_SPINNER_POSITION = 1;

    @MoviesSortOrder
    public static int posToOrder(final int position) {
        switch (position) {
            case MOST_POPULAR_SPINNER_POSITION:
                return MoviesSortOrder.MOST_POPULAR;
            case HIGHEST_RATED_SPINNER_POSITION:
                return MoviesSortOrder.HIGHEST_RATED;
            default:
                throw new IllegalArgumentException("Undefined spinner position");
        }
    }

    public static int orderToPos(@MoviesSortOrder final int sortOrder) {
        switch (sortOrder) {
            case MoviesSortOrder.HIGHEST_RATED:
                return HIGHEST_RATED_SPINNER_POSITION;
            case MoviesSortOrder.MOST_POPULAR:
                return MOST_POPULAR_SPINNER_POSITION;
            default:
                throw new IllegalArgumentException("Undefined movies sort order");
        }
    }
}
