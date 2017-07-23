package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

public class CashOutflowDto {

    private LocalDate flowDate;
    private String flowType;
    private Long flowId;
    private Money flowSum;

    public CashOutflowDto(LocalDate flowDate, String flowType, Long flowId, Money flowSum) {
        this.flowDate = flowDate;
        this.flowType = flowType;
        this.flowId = flowId;
        this.flowSum = flowSum;
    }

    public LocalDate getFlowDate() {
        return flowDate;
    }

    public String getFlowType() {
        return flowType;
    }

    public Long getFlowId() {
        return flowId;
    }

    public Money getFlowSum() {
        return flowSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashOutflowDto that = (CashOutflowDto) o;

        if (!flowDate.equals(that.flowDate)) return false;
        if (flowType != that.flowType) return false;
        if (!flowId.equals(that.flowId)) return false;
        return flowSum != null ? flowSum.equals(that.flowSum) : that.flowSum == null;
    }

    @Override
    public int hashCode() {
        int result = flowId.hashCode();
        return result;
    }
}
