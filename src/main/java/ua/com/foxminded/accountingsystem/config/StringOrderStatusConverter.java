package ua.com.foxminded.accountingsystem.config;

import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.OrderStatus;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringOrderStatusConverter implements Converter<String, OrderStatus> {

    @Override
    public OrderStatus convert(String s) {

        for (OrderStatus status : OrderStatus.values()) {
            if (status.name().equals(s)) {
                return OrderStatus.valueOf(s);
            }
        }
        return null;
    }
}
