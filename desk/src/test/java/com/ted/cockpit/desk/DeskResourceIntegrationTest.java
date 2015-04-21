package com.ted.cockpit.desk;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ted.cockpit.desk.entity.Desk;
import com.ted.cockpit.desk.resource.DeskResource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({"server.port=0", "management.port=0"})
public class DeskResourceIntegrationTest {

	private MockMvc mvc;

	@Autowired
	private DeskResource deskResource;
	
	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(deskResource).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateDesk() throws Exception {
		Desk desk = new Desk();
		desk.setDeskNumber(1);
		desk.setRoom("A1");
		MvcResult result = mvc.perform(
				post("/desk").contentType(MediaType.APPLICATION_JSON).content(
						new ObjectMapper().writeValueAsString(desk)))
				.andExpect(status().isCreated()).andReturn();
		
		Desk response = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Desk.class);
		assertNotNull(response);
		assertNotNull(response.getId());
	}
	
}