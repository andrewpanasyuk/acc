package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.PaymentType;

public class ClientViewDTO {

    private Long clientId;
    private String firstName;
    private String lastName;
    private Long orderId;
    private PaymentType contractPaymentType;

    public ClientViewDTO(Long clientId, String firstName, String lastName, Long orderId, PaymentType contractPaymentType) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.orderId = orderId;
        this.contractPaymentType = contractPaymentType;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public PaymentType getContractPaymentType() {
        return contractPaymentType;
    }
}
