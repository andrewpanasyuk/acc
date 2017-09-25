package ua.com.foxminded.accountingsystem.service.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.com.foxminded.accountingsystem.model.Invoice;

public class InvoiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Invoice.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Invoice invoice = (Invoice) o;
        if (invoice.getPaymentPeriodFrom().plusMonths(1).minusDays(1).isBefore(invoice.getPaymentPeriodTo())) {
            errors.reject("Period must be no more than 1 month (minus 1 day)!");
        }
        if (invoice.getPaymentPeriodFrom().isAfter(invoice.getPaymentPeriodTo())){
            errors.reject("Invalid date range!");
        }
    }
}
