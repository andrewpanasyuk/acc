package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.util.List;

public interface DealService {

    void makeNew(Deal deal);

    void makeActive(Deal deal);

    void makeWaiting(Deal deal);

    void makeFrozen(Deal deal);

    void makeRefused(Deal deal);

    void makeRejected(Deal deal);

    void makeCompleted(Deal deal);

    Deal createDealByClientId(Long id);

    void delete(Deal deal);

    Deal save(Deal deal);

    Deal findOne(Long id);

    List<Deal> findAll();

    List<Deal> findDealsByStatus(DealStatus dealStatus);
}
