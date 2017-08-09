package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.Deal;
import ua.com.foxminded.accountingsystem.model.DealQueue;
import ua.com.foxminded.accountingsystem.model.Priority;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DealQueueRepositoryTest extends AbstractRepositoryTest<DealQueueRepository> {

    private static DealQueue dealQueue;
    private static DealQueue dealQueue_1;

    @Before
    public void init() {
        Deal deal = new Deal();
        deal.setId(1L);

        Deal deal_1 = new Deal();
        deal_1.setId(2L);

        dealQueue = new DealQueue();
        dealQueue.setId(1L);
        dealQueue.setDeal(deal);
        dealQueue.setPriority(Priority.NORMAL);
        dealQueue.setQueuingDate(LocalDate.of(2010, 01, 24));
        dealQueue.setCreatedBy("system");
        dealQueue.setCreatedDate(LocalDateTime.now());

        dealQueue_1 = new DealQueue();
        dealQueue_1.setId(2L);
        dealQueue_1.setDeal(deal_1);
        dealQueue_1.setPriority(Priority.HIGH);
        dealQueue_1.setQueuingDate(LocalDate.of(2015, 10, 01));
    }

    @Test
    @Commit
    @DataSet(value = "queues/empty.xml", disableConstraints = true, cleanBefore = true)
    @ExpectedDataSet(value = "queues/expected-queues.xml", ignoreCols = {"created_by", "created_date"})
    public void addDeal() {
        repository.save(dealQueue);
    }

    @Test
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    public void findAllDealTest() {
        assertEquals(2, repository.findAll().size());
    }

    @Test
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    public void findDealByIdTest() {
        assertEquals(dealQueue_1, repository.findOne(2L));
    }

    @Test
    @Commit
    @DataSet(value = "queues/stored-queues.xml", disableConstraints = true)
    @ExpectedDataSet(value = "queues/expected-queues.xml")
    public void deleteDealByIdTest() {
        repository.delete(2L);
    }
}
