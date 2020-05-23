package br.com.api.movies.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    /**
     * ObjectNotFoundException
     *
     * @param message
     */
    public ObjectNotFoundException(String message) {
        super(message);
    }

    /**
     * ObjectNotFoundException
     *
     * @param message
     * @param cause
     */
    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}