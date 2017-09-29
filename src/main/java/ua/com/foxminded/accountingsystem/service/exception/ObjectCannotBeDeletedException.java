package ua.com.foxminded.accountingsystem.service.exception;

public class ObjectCannotBeDeletedException extends RuntimeException{
    public ObjectCannotBeDeletedException(String message) {
        super(message);
    }
}
