package ua.com.foxminded.accountingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Price;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class InitServices {

    @Autowired
    private ServiceRepository repository;

    @PostConstruct
    public void initTestServices(){
        Service service1 = new Service();
        Service service2 = new Service();
        Price price1 = new Price();
        Price price2 = new Price();
        Price price3 = new Price();
        Price price4 = new Price();

        price1.setPrice(3000);
        price1.setCurrency(Currency.EUR);

        price2.setPrice(2000);
        price2.setCurrency(Currency.UAH);

        price3.setPrice(6000);
        price3.setCurrency(Currency.UAH);
        price4.setPrice(5000);
        price4.setCurrency(Currency.USD);

        List<Price> prices1 = new ArrayList<>();
        prices1.add(price1);
        prices1.add(price2);

        List<Price> prices2 = new ArrayList<>();
        prices2.add(price3);
        prices2.add(price4);

        Price employyRate1 = new Price();
        employyRate1.setPrice(3000);
        employyRate1.setCurrency(Currency.UAH);

        Price employyRate2 = new Price();
        employyRate2.setPrice(4000);
        employyRate2.setCurrency(Currency.UAH);


        service1.setServiceName("java");
        service1.setDescription("java description");
        service1.setPrices(prices1);
        service1.setEmployeeRate(employyRate1);

        service2.setServiceName("javascript");
        service2.setDescription("javascript description");
        service2.setPrices(prices2);
        service2.setEmployeeRate(employyRate2);

        repository.save(service1);
        repository.save(service2);
    }
}
