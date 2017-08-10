package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.Consultancy;

import java.util.List;
import java.util.Map;

public interface DealQueueService {

    DealQueue createQueueByDealId(Long id);

    void delete(DealQueue dealQueue);

    void delete(Long id);

    DealQueue save(DealQueue dealQueue);

    DealQueue findOne(Long id);

    List<DealQueue> findAll();

    Map<Consultancy, List<DealQueue>> findAllGroupByConsultancy();

    DealQueue findQueueByDeal(Deal deal);

}
