package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;

import java.time.LocalDate;

public class CashInflowDto {

    private LocalDate paymentDate;
    private String paidService;
    private Long paymentId;
    private Money paymentSum;

    public CashInflowDto(LocalDate paymentDate, String paidService, Long paymentId, Money paymentSum) {
        this.paymentDate = paymentDate;
        this.paidService = paidService;
        this.paymentId = paymentId;
        this.paymentSum = paymentSum;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaidService() {
        return paidService;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Money getPaymentSum() {
        return paymentSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashInflowDto that = (CashInflowDto) o;

        if (!paymentDate.equals(that.paymentDate)) return false;
        if (paidService != that.paidService) return false;
        if (!paymentId.equals(that.paymentId)) return false;
        return paymentSum != null ? paymentSum.equals(that.paymentSum) : that.paymentSum == null;
    }

    @Override
    public int hashCode() {
        int result = paymentId.hashCode();
        return result;
    }
}
