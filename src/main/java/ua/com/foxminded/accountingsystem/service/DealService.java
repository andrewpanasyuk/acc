package ua.com.foxminded.accountingsystem.service;

import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;

import java.util.List;

public interface DealService {

    Deal createDealByClientId(Long id);

    void delete(Deal deal);

    Deal save(Deal deal);

    Deal findOne(Long id);

    List<Deal> findAll();

    void freeze(Long id);

    List<Deal> findDealsByStatus(DealStatus dealStatus);

    void close(Deal deal, DealStatus dealStatus);

}
