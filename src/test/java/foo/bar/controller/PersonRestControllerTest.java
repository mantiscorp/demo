/**
 * 
 */
package foo.bar.controller;

import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import foo.bar.data.dto.PersonDTO;
import foo.bar.data.model.Person;
import foo.bar.service.PersonService;

/**
 */
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class PersonRestControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PersonRestController personController;

	@Mock
	private PersonService personService;

	private final ObjectMapper om = new ObjectMapper();

	@Before
	public void beforeEachTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	public void testGetAllPersons() throws Exception {
		Mockito.when(personService.getAll()).thenReturn(Collections.singletonList(createTestPerson()));
		Assert.assertTrue(om.readValue(mockMvc.perform(MockMvcRequestBuilders.get(PersonRestController.URL))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString(),
				new TypeReference<Collection<Person>>() {
				}).size() == 1);
	}

	@Test
	public void testGetFirstPerson() throws Exception {
		Mockito.when(personService.getById(Mockito.anyLong())).thenAnswer(inv -> createTestPerson(inv.getArgument(0)));
		mockMvc.perform(MockMvcRequestBuilders.get(PersonRestController.URL + "/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testCreatePerson() throws Exception {
		Mockito.when(personService.save(Mockito.any())).thenReturn(createTestPerson());
		mockMvc.perform(MockMvcRequestBuilders.post(PersonRestController.URL).contentType(MediaType.APPLICATION_JSON)
				.content(om.writeValueAsString(createTestPerson())))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	private static PersonDTO createTestPerson() {
		return createTestPerson(null);
	}

	private static PersonDTO createTestPerson(Long id) {
		PersonDTO result = new PersonDTO();
		result.setId(id);
		result.setFirstName("Winnie");
		result.setLastName("the Pooh");
		return result;
	}
}
