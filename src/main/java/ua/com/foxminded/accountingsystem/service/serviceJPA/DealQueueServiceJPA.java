package ua.com.foxminded.accountingsystem.service.serviceJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.Priority;
import ua.com.foxminded.accountingsystem.repository.ContractRepository;
import ua.com.foxminded.accountingsystem.repository.DealQueueRepository;
import ua.com.foxminded.accountingsystem.repository.DealRepository;
import ua.com.foxminded.accountingsystem.repository.ConsultancyRepository;
import ua.com.foxminded.accountingsystem.service.DealQueueService;
import ua.com.foxminded.accountingsystem.service.DealService;

import javax.transaction.Transactional;
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
    private final ConsultancyRepository consultancyRepository;
    private final DealService dealService;
    private final ContractRepository contractRepository;

    @Autowired
    public DealQueueServiceJPA(DealQueueRepository dealQueueRepository, DealRepository dealRepository,
                               ConsultancyRepository consultancyRepository, DealService dealService,
                               ContractRepository contractRepository) {
        this.dealQueueRepository = dealQueueRepository;
        this.dealRepository = dealRepository;
        this.consultancyRepository = consultancyRepository;
        this.dealService = dealService;
        this.contractRepository = contractRepository;
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
    public Map<Consultancy, List<DealQueue>> findAllGroupByConsultancy() {
        Map<Consultancy, List<DealQueue>> queuesByConsultancy = new HashMap<>();
        List<DealQueue> dealQueues = dealQueueRepository.findAll();
        Collections.sort(dealQueues);
        List<Consultancy> consultancies = consultancyRepository.findAll();
        consultancies.forEach(consultancy -> queuesByConsultancy.put(consultancy, new ArrayList<>()));
        dealQueues.forEach(dealQueue -> queuesByConsultancy.get(dealQueue.getDeal().getConsultancy()).add(dealQueue));
        return queuesByConsultancy;

    }

    @Override
    public DealQueue findQueueByDeal(Deal deal) {
        return dealQueueRepository.findByDeal(deal);
    }

    @Transactional
    @Override
    public void deleteQueue(Long id, DealStatus cause) {
        DealQueue dealQueue = dealQueueRepository.findOne(id);
        Deal deal = dealQueue.getDeal();
        if (cause == null) {
            dealService.changeDealStatus(deal.getId(), getPreviousDealStatus(deal));
        } else {
            dealService.changeDealStatus(deal.getId(), cause);
        }
        dealQueueRepository.delete(dealQueue);
    }

    @Override
    public DealQueue createQueueByDealId(Long id) {
        Deal deal = dealRepository.findOne(id);
        DealQueue dealQueue = new DealQueue();
        dealQueue.setQueuingDate(LocalDate.now());
        if (deal.getStatus().equals(DealStatus.NEW)){
            dealQueue.setPriority(Priority.NORMAL);
        } else {
            dealQueue.setPriority(Priority.HIGH);
        }
        dealService.changeDealStatus(deal.getId(),DealStatus.WAITING);
//        deal.setStatus(DealStatus.WAITING);
        dealQueue.setDeal(deal);
        dealQueueRepository.save(dealQueue);
        return dealQueue;
    }

    private DealStatus getPreviousDealStatus(Deal deal) {
        if (contractRepository.existsContractByDealId(deal.getId())) {
            return DealStatus.FROZEN;
        } else {
            return DealStatus.NEW;
        }
    }
}
