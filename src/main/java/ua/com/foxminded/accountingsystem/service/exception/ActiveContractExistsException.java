package ua.com.foxminded.accountingsystem.service.exception;

public class ActiveContractExistsException extends RuntimeException {
    public ActiveContractExistsException(String message) {
        super(message);
    }
}
