package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.Money;

import java.time.LocalDate;

public class PaymentDto {

    private Long id;
    private Long invoiceId;
    private LocalDate datePaid;
    private Money sum;


    public PaymentDto(Long id, Long invoiceId, LocalDate datePaid, Money sum) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.datePaid = datePaid;
        this.sum = sum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(LocalDate datePaid) {
        this.datePaid = datePaid;
    }

    public Money getSum() {
        return sum;
    }

    public void setSum(Money sum) {
        this.sum = sum;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }
}
