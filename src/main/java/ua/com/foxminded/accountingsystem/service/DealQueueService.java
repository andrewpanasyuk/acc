package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.Service;

import java.util.List;
import java.util.Map;

public interface DealQueueService {

    DealQueue createQueueByDealId(Long id);

    void delete(DealQueue dealQueue);

    void delete(Long id);

    DealQueue save(DealQueue dealQueue);

    DealQueue findOne(Long id);

    List<DealQueue> findAll();

    Map<Service, List<DealQueue>> findAllGroupByService();

    DealQueue findQueueByDeal(Deal deal);

}
