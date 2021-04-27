package org.firstmvc.util;

import org.firstmvc.config.PersistenceConfig;
import org.firstmvc.config.WebConfig;
import org.firstmvc.config.securityConfig.SecurityConfig;
import org.firstmvc.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        WebConfig.class,
        PersistenceConfig.class,
        SecurityConfig.class
})
@WebAppConfiguration
public class MainControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void indexPage() throws Exception {
        String name = "nobody";
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", name))
                .andExpect(view().name("/index"));
    }

    @Test
    public void usersPage() throws Exception {
        mvc.perform(get("/users"))
                .andExpect(status().isOk())
//                .andExpect(model().attribute("users", new ArrayList<User>()))
                .andExpect(view().name("/users"));
    }

}
