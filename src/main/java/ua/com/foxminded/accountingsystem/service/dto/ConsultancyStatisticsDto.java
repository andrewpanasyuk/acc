package ua.com.foxminded.accountingsystem.service.dto;

public class ConsultancyStatisticsDto {

    private String consultancyName;
    private long countActiveCases;
    private long countFrozenCases;
    private long countCompletedCases;

    public String getConsultancyName() {
        return consultancyName;
    }

    public void setConsultancyName(String consultancyName) {
        this.consultancyName = consultancyName;
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
