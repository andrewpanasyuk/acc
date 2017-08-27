package ua.com.foxminded.accountingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.service.dto.ClientDealDto;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long>{

    List<Deal> findDealsByStatus(DealStatus dealStatus);

    long countDealsByStatusAndConsultancy(DealStatus dealStatus, Consultancy consultancy);

    @Query("select distinct new ua.com.foxminded.accountingsystem.service.dto.ClientDealDto"
        + "(d.id, d.consultancy.name, d.status, d.openDate, e.id, e.firstName, e.lastName) "
        + "from  Contract c inner join c.employee e right outer join c.deal d "
        + "where d.client.id = ?1 "
        + "and (c.contractDate  = (select max(cMaxDate.contractDate) "
        + "                        from Contract cMaxDate "
        + "                        where cMaxDate.deal.client.id = ?1 "
        + "                        and cMaxDate.deal.id = c.deal.id) "
        + "     or c.contractDate is null)")
    List<ClientDealDto> findDealsAndMentorsByClient(Long clientId);
}
