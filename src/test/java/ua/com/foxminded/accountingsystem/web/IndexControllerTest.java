package ua.com.foxminded.accountingsystem.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ua.com.foxminded.accountingsystem.service.UserService;
import ua.com.foxminded.accountingsystem.web.controller.IndexController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @Mock
    private UserService userService;

    private IndexController indexController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        indexController = new IndexController(userService);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void requestForIndexPageIsSuccessfullyProcessed() throws Exception {
        this.mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(forwardedUrl("index"));
    }


}
