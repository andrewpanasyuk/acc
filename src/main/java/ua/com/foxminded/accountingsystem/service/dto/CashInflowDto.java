package ua.com.foxminded.accountingsystem.service.dto;

import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;

import java.time.LocalDate;

public class CashInflowDto {

    private LocalDate date;
    private String type;
    private Long id;
    private Money sum;

    public CashInflowDto(LocalDate date, String type, Long id, Money sum) {
        this.date = date;
        this.type = type;
        this.id = id;
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }

    public Money getSum() {
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        CashInflowDto that = (CashInflowDto) o;

        if (!date.equals(that.date)) return false;
        if (type != that.type) return false;
        if (!id.equals(that.id)) return false;
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        return result;
    }
}
