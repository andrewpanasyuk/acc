package ua.com.foxminded.accountingsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository repository;

    @Test
    public void testFindAll(){
        assertEquals(2, repository.findAll().size());
    }

    @Test
    public void testFindOne(){
        assertEquals("java", repository.findOne(1L).getName());
    }

}
