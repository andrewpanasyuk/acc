package ua.com.foxminded.accountingsystem.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.DealStatus;

@Component
public class DealStatusAttributeConverter implements Converter<String, DealStatus> {
    @Override
    public DealStatus convert(String s) {
        try {
            return DealStatus.valueOf(s);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
