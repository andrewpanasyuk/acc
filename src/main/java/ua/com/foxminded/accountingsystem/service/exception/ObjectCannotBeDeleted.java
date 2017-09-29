package ua.com.foxminded.accountingsystem.service.exception;

public class ObjectCannotBeDeleted extends RuntimeException{
    public ObjectCannotBeDeleted(String message) {
        super(message);
    }
}
