package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ClientOfEmployeeDto implements Comparable<ClientOfEmployeeDto> {

    private Long clientId;
    private String firstName;
    private String lastName;
    private Long dealId;
    private PaymentType contractPaymentType;
    private String consultancyName;
    private LocalDate createDate;
    private LocalDate closeDate;
    private DealStatus dealStatus;

    public ClientOfEmployeeDto(Long clientId, String firstName, String lastName, Long dealId,
                               PaymentType contractPaymentType, String consultancyName, LocalDate createDate,
                               LocalDate closeDate, DealStatus dealStatus) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dealId = dealId;
        this.contractPaymentType = contractPaymentType;
        this.consultancyName = consultancyName;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.dealStatus = dealStatus;
    }

    public ClientOfEmployeeDto(Long clientId, String firstName, String lastName, Long dealId, PaymentType contractPaymentType) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dealId = dealId;
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

    public Long getDealId() {
        return dealId;
    }

    public String getConsultancyName() {
        return consultancyName;
    }

    public PaymentType getContractPaymentType() {
        return contractPaymentType;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public String getDealStatus() {
        return dealStatus.toString();
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
        if (!dealId.equals(that.dealId)) return false;
        return contractPaymentType == that.contractPaymentType;
    }

    @Override
    public int hashCode() {
        if (clientId == null) {
            return 31;
        }
        return clientId.hashCode();
    }

    @Override
    public String toString() {
        return "ClientOfEmployeeDto{" +
            "clientId=" + clientId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dealId=" + dealId +
            ", contractPaymentType=" + contractPaymentType +
            ", consultancyName='" + consultancyName + '\'' +
            '}';
    }

    @Override
    public int compareTo(ClientOfEmployeeDto clientOfEmployeeDto) {
        List<Enum> enumValues = Arrays.asList(DealStatus.values());
        return enumValues.indexOf(DealStatus.valueOf(getDealStatus()))
            - (enumValues.indexOf(DealStatus.valueOf(clientOfEmployeeDto.getDealStatus())));
    }
}
