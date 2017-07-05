package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.PaymentType;

public class ClientOfEmployeeDto {

    private Long clientId;
    private String firstName;
    private String lastName;
    private Long orderId;
    private PaymentType contractPaymentType;

    public ClientOfEmployeeDto(Long clientId, String firstName, String lastName, Long orderId, PaymentType contractPaymentType) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        ClientOfEmployeeDto that = (ClientOfEmployeeDto) o;

        if (!clientId.equals(that.clientId)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!orderId.equals(that.orderId)) return false;
        return contractPaymentType == that.contractPaymentType;
    }

    @Override
    public int hashCode() {
        if (clientId == null) {
            return 31;
        }
        return clientId.hashCode();
    }
}
