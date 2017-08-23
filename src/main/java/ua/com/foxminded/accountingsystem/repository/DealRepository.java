package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.service.dto.DealOfClientDto;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long>{

    List<Deal> findDealsByStatus(DealStatus dealStatus);

    long countDealsByStatusAndConsultancy(DealStatus dealStatus, Consultancy consultancy);

//    @Query("select distinct new ua.com.foxminded.accountingsystem.service.dto.DealOfClientDto"
//        + "(deal.id, deal.consultancy.name, deal.status, deal.openDate, "
//        + "contract.employee.id, contract.employee.firstName, contract.employee.lastName) "
//        + "from Deal deal left join Contract contract on contract.deal = deal "
//        + "where deal.client.id = ?1 ")
//        + "and contract.contractDate = (select max(contractInner.contractDate) from Contract contractInner, Deal dealInner where contractInner.deal = dealInner and dealInner.client.id = ?1) ")
    List<DealOfClientDto> findDealsByClient(Long clientId);

}
