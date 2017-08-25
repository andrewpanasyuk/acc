package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Client;
import ua.com.foxminded.accountingsystem.model.Consultancy;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealStatus;
import ua.com.foxminded.accountingsystem.model.PaymentType;
import ua.com.foxminded.accountingsystem.service.dto.ClientOfEmployeeDto;
import ua.com.foxminded.accountingsystem.service.dto.DealOfClientDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DealRepositoryTest extends AbstractRepositoryTest<DealRepository> {

    private static Deal deal;
    private static Deal deal_1;

    @Before
    public void init() {
        Consultancy consultancy = new Consultancy();
        consultancy.setId(1L);

        Consultancy consultancy_1 = new Consultancy();
        consultancy_1.setId(2L);

        Client client = new Client();
        client.setId(1L);

        Client client_1 = new Client();
        client_1.setId(2L);

        deal = new Deal();
        deal.setId(1L);
        deal.setStatus(DealStatus.ACTIVE);
        deal.setConsultancy(consultancy);
        deal.setClient(client);
        deal.setOpenDate(LocalDate.of(2017, 01, 24));
        deal.setCloseDate(LocalDate.of(2017, 01, 25));
        deal.setCreatedBy("system");
        deal.setCreatedDate(LocalDateTime.now());

        deal_1 = new Deal();
        deal_1.setId(2L);
        deal_1.setStatus(DealStatus.WAITING);
        deal_1.setConsultancy(consultancy_1);
        deal_1.setClient(client_1);
        deal_1.setOpenDate(LocalDate.of(2010, 01, 24));
    }


    @Test
    @Commit
    @DataSet(value = "deals/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "deals/expected-deals.xml", ignoreCols = {"created_by", "created_date"})
    public void addDeal() {
        repository.save(deal);
    }

    @Test
    @DataSet(value = "deals/stored-deals.xml", disableConstraints = true)
    public void findAllDealsTest() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "deals/stored-deals.xml" , disableConstraints = true)
    public void findDealByIdTest() {
        assertEquals(deal_1, repository.findOne(2L));
    }

    @Test
    @Commit
    @DataSet(value = "deals/stored-deals.xml", disableConstraints = true)
    @ExpectedDataSet(value = "deals/expected-deals.xml")
    public void deleteDealByIdTest() {
        repository.delete(2L);
    }

    @Test
    @DataSet(value = "deals/stored-deals.xml", disableConstraints = true, cleanBefore = true)
    public void getDealsByStatus() {
        assertEquals(1, repository.findDealsByStatus(DealStatus.ACTIVE).size());
        assertThat(repository.findDealsByStatus(DealStatus.ACTIVE), hasItems(deal));
        assertEquals(1, repository.findDealsByStatus(DealStatus.WAITING).size());
        assertThat(repository.findDealsByStatus(DealStatus.WAITING), hasItems(deal_1));
    }

    @Test
    @DataSet(value = "deals/stored-deals-contracts.xml", disableConstraints = true)
    public void findDealsWithMentorsByClient() {

        DealOfClientDto expectedDeal1 = new DealOfClientDto(3L,"javascript", DealStatus.ACTIVE, LocalDate.of(2017,3,24), null, null, null);
        DealOfClientDto expectedDeal2 = new DealOfClientDto(5L,"angular", DealStatus.FROZEN, LocalDate.of(2017,5,24), 2L, "Anton", "Teplinsky");
        DealOfClientDto expectedDeal3 = new DealOfClientDto(7L,"java", DealStatus.ACTIVE, LocalDate.of(2017,3,14), 3L, "Andrey", "Gubar");
        DealOfClientDto expectedDeal4 = new DealOfClientDto(9L,"angular", DealStatus.NEW, LocalDate.of(2017,8,23), null, null, null);
        DealOfClientDto expectedDeal5 = new DealOfClientDto(1L,"java", DealStatus.ACTIVE, LocalDate.of(2017,1,24), 1L, "Sergey", "Fedotov");
        DealOfClientDto expectedDeal6 = new DealOfClientDto(2L,"javascript", DealStatus.ACTIVE, LocalDate.of(2017,2,24), null, null, null);

        assertEquals(4, repository.findDealsWithMentorsByClient(3L).size());
        assertThat(repository.findDealsWithMentorsByClient(3L), hasItems(expectedDeal1, expectedDeal2, expectedDeal3, expectedDeal4));
        List<DealOfClientDto> deals = repository.findDealsWithMentorsByClient(1L);

        assertEquals(2, repository.findDealsWithMentorsByClient(1L).size());
        assertThat(repository.findDealsWithMentorsByClient(1L), hasItems(expectedDeal5, expectedDeal6));
    }
}
