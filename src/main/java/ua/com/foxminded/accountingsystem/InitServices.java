package ua.com.foxminded.accountingsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


//@Component
//public class InitServices {
//
//    @Autowired
//    private ServiceRepository repository;
//
//    @PostConstruct
//    public void initTestServices(){
//        Service service1 = new Service();
//        Service service2 = new Service();
//        Money money1 = new Money();
//        Money money2 = new Money();
//        Money money3 = new Money();
//        Money money4 = new Money();
//        Money money5 = new Money();
//        Money money6 = new Money();
//
//        money1.setPrice(3000);
//        money1.setCurrency(Currency.EUR);
//
//        money2.setPrice(2000);
//        money2.setCurrency(Currency.UAH);
//
//        money3.setPrice(6000);
//        money3.setCurrency(Currency.UAH);
//
//        money4.setPrice(5000);
//        money4.setCurrency(Currency.USD);
//
//        money5.setPrice(5000);
//        money5.setCurrency(Currency.USD);
//
//        money6.setPrice(5000);
//        money6.setCurrency(Currency.EUR);
//
//        List<Money> prices1 = new ArrayList<>();
//        prices1.add(money1);
//        prices1.add(money2);
//        prices1.add(money5);
//
//        List<Money> prices2 = new ArrayList<>();
//        prices2.add(money3);
//        prices2.add(money4);
//        prices2.add(money6);
//
//        Money employyRate1 = new Money();
//        employyRate1.setPrice(3000);
//        employyRate1.setCurrency(Currency.UAH);
//
//        Money employyRate2 = new Money();
//        employyRate2.setPrice(4000);
//        employyRate2.setCurrency(Currency.UAH);
//
//
//        service1.setServiceName("java");
//        service1.setDescription("java description");
//        service1.setMonies(prices1);
//        service1.setEmployeeRate(employyRate1);
//
//        service2.setServiceName("javascript");
//        service2.setDescription("javascript description");
//        service2.setMonies(prices2);
//        service2.setEmployeeRate(employyRate2);
//
//        repository.save(service1);
//        repository.save(service2);
//    }
//}
