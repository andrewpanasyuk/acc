package ua.com.foxminded.accountingsystem.config;

import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringOrderStatusConverter implements Converter<String, OrderStatus> {

    @Override
    public OrderStatus convert(String s) {
        try {
            return OrderStatus.valueOf(s);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
