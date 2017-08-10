package ua.com.foxminded.accountingsystem.config;

import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import org.springframework.core.convert.converter.Converter;

@Component
public class StringDealStatusConverter implements Converter<String, DealStatus> {

    @Override
    public DealStatus convert(String s) {
        try {
            return DealStatus.valueOf(s);
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }
}
