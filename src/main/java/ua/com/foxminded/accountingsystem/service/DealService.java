package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.util.List;

public interface DealService {

    void changeStatus(Deal deal, DealStatus dealStatus);

    void setNew(Deal deal);

    void setActive(Deal deal);

    void setWaiting(Deal deal);

    void setFrozen(Deal deal);

    void setRefused(Deal deal);

    void setRejected(Deal deal);

    void setCompleted(Deal deal);

    Deal createDealByClientId(Long id);

    void delete(Deal deal);

    Deal save(Deal deal);

    Deal findOne(Long id);

    List<Deal> findAll();

    List<Deal> findDealsByStatus(DealStatus dealStatus);
}
