package ua.com.foxminded.accountingsystem.service.exception;

public class ContractDateExistsException extends RuntimeException {
    public ContractDateExistsException(String message) {
        super(message);
    }
}
