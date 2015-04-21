package com.ted.cockpit.desk;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ted.cockpit.desk.entity.Desk;
import com.ted.cockpit.desk.resource.DeskResource;
import com.ted.cockpit.desk.service.DeskService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class DeskResourceTest {

	private MockMvc mvc;

	@Mock
	private DeskService deskService;

	@InjectMocks
	private DeskResource deskResource;
	
	@Before
	public void setUp() throws Exception {
		deskResource = new DeskResource();
		mvc = MockMvcBuilders.standaloneSetup(deskResource).build();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateDesk() throws Exception {
		Desk desk = new Desk();
		desk.setDeskNumber(1);
		desk.setRoom("A1");
		mvc.perform(
				post("/desk").contentType(MediaType.APPLICATION_JSON).content(
						new ObjectMapper().writeValueAsString(desk)))
				.andExpect(status().isCreated());

		verify(deskService).createDesk(Mockito.<Desk> any());
	}
	
}