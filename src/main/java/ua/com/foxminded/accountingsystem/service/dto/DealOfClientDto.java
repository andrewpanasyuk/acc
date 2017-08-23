package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.time.LocalDate;

public class DealOfClientDto {

    private Long dealId;
    private String consultancyName;
    private DealStatus dealStatus;
    private LocalDate dealOpenDate;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;

    public DealOfClientDto(Long dealId, String consultancyName, DealStatus dealStatus,
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

        DealOfClientDto that = (DealOfClientDto) o;

        if (!dealId.equals(that.dealId)) return false;
        if (!consultancyName.equals(that.consultancyName)) return false;
        if (dealStatus != that.dealStatus) return false;
        if (!dealOpenDate.equals(that.dealOpenDate)) return false;
        if (!employeeId.equals(that.employeeId)) return false;
        if (!employeeFirstName.equals(that.employeeFirstName)) return false;
        return employeeLastName.equals(that.employeeLastName);
    }

    @Override
    public int hashCode() {
        if (dealId == null) {
            return 31;
        }
        return dealId.hashCode();
    }
}
