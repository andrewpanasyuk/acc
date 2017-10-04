package ua.com.foxminded.accountingsystem.util.constraints.impl;

import org.apache.commons.beanutils.PropertyUtils;
import ua.com.foxminded.accountingsystem.model.Invoice;
import ua.com.foxminded.accountingsystem.util.constraints.MonthOrLessPeriod;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class MonthOrLessPeriodValidator implements ConstraintValidator<MonthOrLessPeriod, Invoice> {

    private String dateFrom;
    private String dateTo;
    private String message;

    @Override
    public void initialize(MonthOrLessPeriod monthOrLessPeriod) {
        dateFrom = monthOrLessPeriod.dateFrom();
        dateTo = monthOrLessPeriod.dateTo();
        message = monthOrLessPeriod.message();
    }

    @Override
    public boolean isValid(Invoice invoice, ConstraintValidatorContext constraintValidatorContext) {

        boolean valid = true;

        try {
            final LocalDate dateFrom = (LocalDate) PropertyUtils.getProperty(invoice, this.dateFrom);
            final LocalDate dateTo = (LocalDate) PropertyUtils.getProperty(invoice, this.dateTo);

            if (dateFrom.isAfter(dateTo)) {
                valid = false;
                message = "End date can't be before start date";
            } else if (dateFrom.plusMonths(1).minusDays(1).isBefore(dateTo)) {
                valid = false;
                message = "The period should be less than a month";
            }

        } catch (final Exception ignore) {
            // ignore
        }
        if (!valid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(message).addPropertyNode(dateTo).addConstraintViolation();
        }
        return valid;
    }
}
