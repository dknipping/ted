package com.prodyna.cockpit.reservation;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.prodyna.cockpit.reservation.controller.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Created by mfroehlich on 23.04.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class HelloWorldTest {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
    }

    @Test
    public void getHelloWorld() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/helloworld").accept(MediaType.APPLICATION_JSON)).andExpect(
                status().isCreated()).andExpect(content().string(equalTo("Hello World!"))).andReturn();
        String helloWorldString = mvcResult.getResponse().getContentAsString();
        System.out.println(helloWorldString);
    }

    @Test
    public void getRoot() throws Exception {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
    }
}
