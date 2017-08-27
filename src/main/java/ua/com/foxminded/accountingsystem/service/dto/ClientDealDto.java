package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.time.LocalDate;

public class ClientDealDto {

    private Long dealId;
    private String consultancyName;
    private DealStatus dealStatus;
    private LocalDate dealOpenDate;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    public ClientDealDto(Long dealId, String consultancyName, DealStatus dealStatus,
                         LocalDate dealOpenDate, Long employeeId, String employeeFirstName, String employeeLastName) {
        this.dealId = dealId;
        this.consultancyName = consultancyName;
        this.dealStatus = dealStatus;
        this.dealOpenDate = dealOpenDate;
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
    }

    public Long getDealId() {
        return dealId;
    }

    public String getConsultancyName() {
        return consultancyName;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public LocalDate getDealOpenDate() {
        return dealOpenDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;

        ClientDealDto that = (ClientDealDto) o;

        if (!dealId.equals(that.dealId)) return false;
        if (!consultancyName.equals(that.consultancyName)) return false;
        if (dealStatus != that.dealStatus) return false;
        if (!dealOpenDate.equals(that.dealOpenDate)) return false;
        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (employeeFirstName != null ? !employeeFirstName.equals(that.employeeFirstName) : that.employeeFirstName != null)
            return false;
        return employeeLastName != null ? employeeLastName.equals(that.employeeLastName) : that.employeeLastName == null;
    }

    @Override
    public int hashCode() {
        if (dealId == null) {
            return 31;
        }
        return dealId.hashCode();
    }
}
