package ua.com.foxminded.accountingsystem.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Commit;
import ua.com.foxminded.accountingsystem.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;

public class UserRepositoryTest extends AbstractRepositoryTest<UserRepository> {

    private static User admin;
    private static User user;

    @BeforeClass
    public static void init() {
        admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq");
        admin.setEnabled(true);
        admin.setEmail("admin@foxminded.com.ua");
        admin.setCreatedBy("system");
        admin.setCreatedDate(LocalDateTime.now());

        user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq");
        user.setEnabled(true);
        user.setEmail("user@foxminded.com.ua");
    }


    @Test
    @Commit
    @DataSet(value = "users/empty.xml")
    @ExpectedDataSet(value = "users/expected-users.xml", ignoreCols = {"created_by", "created_date"})
    public void addUser() {
        repository.save(admin);
    }

    @Test
    @DataSet(value = "users/stored-users.xml")
    public void userCanBeFoundByDifferentMethods() {
        assertThat(repository.findAll(), hasItems(admin, user));
        assertThat(repository.exists(admin.getUsername()), is(true));
    }

    @Test
    @DataSet(value = "users/stored-users.xml")
    public void ifNoUserFoundReturnNull() {
        assertThat(repository.findOne("NoSuchUsername"), nullValue());
    }

    @Test
    @DataSet("users/empty.xml")
    public void ifNoUsersFoundReturnEmptyList() {
        assertThat(repository.findAll(), is(empty()));
    }

    @Test
    @Commit
    @DataSet(value = "users/stored-users.xml")
    @ExpectedDataSet("users/expected-users.xml")
    public void shouldDeleteByEntity() {
        repository.delete(user);
    }

    @Test
    @Commit
    @DataSet(value = "users/stored-users.xml")
    @ExpectedDataSet("users/expected-users.xml")
    public void shouldDeleteById() {
        repository.delete(user.getUsername());
    }
}
