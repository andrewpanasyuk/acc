package ua.com.foxminded.accountingsystem.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.accountingsystem.model.UserRole;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class UserRoleRepositoryTest extends AbstractRepositoryTest{
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void ifThereIsNoRolesWithSuchNameNullIsReturned() {
        assertThat(userRoleRepository.findByRole("unknown"), is(nullValue()));
    }

    @Test
    public void ifRoleFoundItIsReturned() {
        int id = addRoleToDatabase("TEST");
        UserRole role = new UserRole();
        role.setRole("TEST");
        role.setId(id);
        assertEquals(userRoleRepository.findByRole("TEST"), role);
    }

    private int addRoleToDatabase(String role) {
        Map<String, Object> values = new HashMap<>();
        values.put("role", role);
        return addRecordToDatabase("user_role", values);
    }
}
