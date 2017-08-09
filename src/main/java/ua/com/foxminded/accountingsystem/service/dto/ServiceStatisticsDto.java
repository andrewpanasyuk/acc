package ua.com.foxminded.accountingsystem.service.dto;

public class ServiceStatisticsDto {

    private String serviceName;
    private long countActiveCases;
    private long countFrozenCases;
    private long countCompletedCases;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getCountActiveCases() {
        return countActiveCases;
    }

    public void setCountActiveCases(long countActiveCases) {
        this.countActiveCases = countActiveCases;
    }

    public long getCountFrozenCases() {
        return countFrozenCases;
    }

    public void setCountFrozenCases(long countFrozenCases) {
        this.countFrozenCases = countFrozenCases;
    }

    public long getCountCompletedCases() {
        return countCompletedCases;
    }

    public void setCountCompletedCases(long countCompletedCases) {
        this.countCompletedCases = countCompletedCases;
    }
}
