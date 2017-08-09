package ua.com.foxminded.accountingsystem.service.exception;

public class NotEnoughMoneyWithdrawException extends RuntimeException {
    public NotEnoughMoneyWithdrawException(String message) {
        super(message);
    }
}
