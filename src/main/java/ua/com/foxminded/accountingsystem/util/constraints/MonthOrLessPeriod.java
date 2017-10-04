package ua.com.foxminded.accountingsystem.util.constraints;

import ua.com.foxminded.accountingsystem.util.constraints.impl.MonthOrLessPeriodValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = MonthOrLessPeriodValidator.class)
@Documented
public @interface MonthOrLessPeriod {

    String message() default "Not valid period.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String dateFrom();

    String dateTo();
}
