package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

public class CashOutflowDto {

    private LocalDate salaryDate;
    private Long salaryId;
    private Money salarySum;

    public CashOutflowDto(LocalDate salaryDate, Long salaryId, Currency currencyId, Long salarySum) {
        this.salaryDate = salaryDate;
        this.salaryId = salaryId;
        this.salarySum = new Money();
        this.salarySum.setCurrency(currencyId);
        this.salarySum.setAmount(salarySum);
    }

    public LocalDate getSalaryDate() {
        return salaryDate;
    }

    public Long getSalaryId() {
        return salaryId;
    }

    public Money getSalarySum() {
        return salarySum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashOutflowDto that = (CashOutflowDto) o;

        if (!salaryDate.equals(that.salaryDate)) return false;
        if (!salaryId.equals(that.salaryId)) return false;
        return salarySum != null ? salarySum.equals(that.salarySum) : that.salarySum == null;
    }

    @Override
    public int hashCode() {
        int result = salaryId.hashCode();
        return result;
    }
}
