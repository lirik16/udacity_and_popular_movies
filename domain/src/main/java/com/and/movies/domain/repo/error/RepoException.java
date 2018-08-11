package com.and.movies.domain.repo.error;

public class RepoException extends RuntimeException {

    public RepoException() {
        super();
    }

    public RepoException(final String message) {
        super(message);
    }

    public RepoException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RepoException(final Throwable cause) {
        super(cause);
    }
}
