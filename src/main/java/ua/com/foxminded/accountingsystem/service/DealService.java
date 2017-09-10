package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.util.List;

public interface DealService {

    void changeDealStatus(Deal deal, DealStatus newStatus);

    Deal createDealByClientId(Long id);

    void delete(Deal deal);

    Deal save(Deal deal);

    Deal findOne(Long id);

    List<Deal> findAll();

    List<Deal> findDealsByStatus(DealStatus dealStatus);
}
