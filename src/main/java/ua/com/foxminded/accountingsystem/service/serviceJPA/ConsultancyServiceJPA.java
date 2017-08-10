package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Money;
import ua.com.foxminded.accountingsystem.repository.ConsultancyRepository;
import ua.com.foxminded.accountingsystem.service.ConsultancyService;

import java.util.List;

@Service
public class ConsultancyServiceJPA implements ConsultancyService {

    private static final Logger log = LoggerFactory.getLogger(ConsultancyServiceJPA.class);

    private final ConsultancyRepository repository;

    @Autowired
    public ConsultancyServiceJPA(ConsultancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Consultancy> findAll() {
        return repository.findAll();
    }

    @Override
    public Consultancy save(Consultancy consultancy) {
        return repository.save(consultancy);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }

    @Override
    public Consultancy findOne(long id) {
        return repository.findOne(id);
    }

    @Override
    public Consultancy prepareNewConsultancy() {
        Consultancy consultancy = new Consultancy();
        for (Currency currency : Currency.values()) {
            consultancy.getPrices().add(new Money(0, currency));
        }
        consultancy.setEmployeeRate(new Money(0, Currency.UAH));
        return consultancy;
    }

}
