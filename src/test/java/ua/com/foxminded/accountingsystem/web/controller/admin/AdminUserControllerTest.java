package ua.com.foxminded.accountingsystem.web.controller.admin;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import ua.com.foxminded.accountingsystem.model.User;
import ua.com.foxminded.accountingsystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsMapContaining.hasEntry;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import static ua.com.foxminded.accountingsystem.service.dto.converter.UserConverter.convertToDtoList;


@RunWith(SpringRunner.class)
public class AdminUserControllerTest {

    @Mock
    private UserService userService;

    private AdminUserController controller;

    private MockMvc mockMvc;

    private static User admin;
    private static User user;
    private static List<User> users;

    @BeforeClass
    public static void initClass() {
        admin = new User();
        admin.setUsername("admin");
        admin.setPassword("$2a$10$E62tjpReNRsZxmyVs1iwZe8UdQeoM91HkIt6YIIBKHniG3Avg6Kyq");
        admin.setEnabled(true);
        admin.setEmail("admin@foxminded.com.ua");

        user = new User();
        user.setUsername("user");
        user.setPassword("$2a$10$DLUI1caX.tFT6u/xCiQzluxo7UvHxYhTivcdU6SyV67Q90qZaJ9Qq");
        user.setEnabled(true);
        user.setEmail("user@foxminded.com.ua");

        users = new ArrayList<>();
        users.add(admin);
        users.add(user);
    }

    @Before
    public void init() {
        controller = new AdminUserController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        when(userService.findAll()).thenReturn(convertToDtoList(users));
        when(userService.findByUsername(admin.getUsername())).thenReturn(admin);
    }

    @Test
    public void usersAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getAllUsers(model), equalTo("admin/users"));
        assertThat(model.asMap(), hasEntry("users", convertToDtoList(users)));
    }

    @Test
    public void usersTitleAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getAllUsers(model), equalTo("admin/users"));
        assertThat(model.asMap(), hasEntry("title", "Users management"));
    }

    @Test
    public void oneUserAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getOneUser(admin.getUsername(), model), equalTo("admin/user"));
        assertThat(model.asMap(), hasEntry("user", admin));
    }

    @Test
    public void userTitleAddedToModel() {
        Model model = new ExtendedModelMap();
        assertThat(controller.getOneUser(admin.getUsername(), model), equalTo("admin/user"));
        assertThat(model.asMap(), hasEntry("title", admin.getUsername() + " management"));
    }
}
