package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Priority;
import ua.com.foxminded.accountingsystem.repository.DealQueueRepository;
import ua.com.foxminded.accountingsystem.repository.DealRepository;
import ua.com.foxminded.accountingsystem.repository.ServiceRepository;
import ua.com.foxminded.accountingsystem.service.DealQueueService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DealQueueServiceJPA implements DealQueueService {

    private final DealQueueRepository dealQueueRepository;
    private final DealRepository dealRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public DealQueueServiceJPA(DealQueueRepository dealQueueRepository, DealRepository dealRepository, ServiceRepository serviceRepository) {
        this.dealQueueRepository = dealQueueRepository;
        this.dealRepository = dealRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public void delete(DealQueue dealQueue) {
        dealQueueRepository.delete(dealQueue);
    }

    @Override
    public void delete(Long id) {
        dealQueueRepository.delete(id);
    }

    @Override
    public DealQueue save(DealQueue dealQueue) {
        return dealQueueRepository.save(dealQueue);
    }

    @Override
    public DealQueue findOne(Long id) {
        return dealQueueRepository.findOne(id);
    }

    @Override
    public List<DealQueue> findAll() {
        List<DealQueue> dealQueues = dealQueueRepository.findAll();
        Collections.sort(dealQueues);
        return dealQueues;
    }

    @Override
    public Map<ua.com.foxminded.accountingsystem.model.Service, List<DealQueue>> findAllGroupByService() {
        Map<ua.com.foxminded.accountingsystem.model.Service, List<DealQueue>> queuesByService = new HashMap<>();
        List<DealQueue> dealQueues = dealQueueRepository.findAll();
        Collections.sort(dealQueues);
        List<ua.com.foxminded.accountingsystem.model.Service> services = serviceRepository.findAll();
        services.forEach(service -> queuesByService.put(service, new ArrayList<>()));
        dealQueues.forEach(dealQueue -> queuesByService.get(dealQueue.getDeal().getService()).add(dealQueue));
        return queuesByService;

    }

    @Override
    public DealQueue findQueueByDeal(Deal deal) {
        return dealQueueRepository.findByDeal(deal);
    }

    public DealQueue createQueueByDealId(Long id) {
        Deal deal = dealRepository.findOne(id);
        DealQueue dealQueue = new DealQueue();
        dealQueue.setQueuingDate(LocalDate.now());
        if (deal.getStatus().equals(DealStatus.NEW)){
            dealQueue.setPriority(Priority.NORMAL);
        } else {
            dealQueue.setPriority(Priority.HIGH);
        }
        deal.setStatus(DealStatus.WAITING);
        dealQueue.setDeal(deal);
        dealQueueRepository.save(dealQueue);
        return dealQueue;
    }
}
