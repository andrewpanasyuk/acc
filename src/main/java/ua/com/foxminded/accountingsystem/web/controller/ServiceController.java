package ua.com.foxminded.accountingsystem.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.foxminded.accountingsystem.model.Currency;
import ua.com.foxminded.accountingsystem.model.Price;
import ua.com.foxminded.accountingsystem.model.Service;
import ua.com.foxminded.accountingsystem.service.ServiceService;

@Controller
@RequestMapping("/admin/service")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private final ServiceService serviceService;

    private Service service;

    @Autowired
    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void initService(Service service){
        this.service = service;
    }

    public void reset(){
        service = new Service();
    }

    public void save(){
        serviceService.save(service);
    }

    public void removePrice(Price price){
        service.getPrices().remove(price);

    }

    public void extend(){
        service.getPrices().add(new Price());
    }

    public Currency[] getCurrencies(){
        return Currency.values();
    }

}
