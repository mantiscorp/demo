/**
 * 
 */
package foo.bar.controller;

import java.io.FileNotFoundException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import foo.bar.data.dto.PersonDTO;
import foo.bar.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 */
@Api("PersonRestController")
@RestController
@RequestMapping(PersonRestController.URL)
public class PersonRestController {

	@ApiOperation(value = "Shows all available Persons")
	@ApiResponses(
		value = {
			@ApiResponse(
				code = 200,
				message = "List of Persons",
				response = PersonDTO.class,
				responseContainer = "List") })
	@GetMapping
	@ResponseBody
	public Collection<PersonDTO> getAll() {
		return personService.getAll();
	}

	@ApiOperation(value = "Shows a Person by id")
	@ApiResponses(
		value = {
			@ApiResponse(code = 200, message = "A Person by id", response = PersonDTO.class),
			@ApiResponse(code = 404, message = "Error message") })
	@GetMapping(URL_ID)
	@ResponseBody
	public PersonDTO getById(@PathVariable long id) {
		try {
			return personService.getById(id);
		} catch (FileNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Creates a new Person")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "A created Person", response = PersonDTO.class) })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PersonDTO create(@RequestBody PersonDTO personDTO) {
		return personService.save(personDTO);
	}

	@ApiOperation(value = "Updates an existing Person")
	@ApiResponses(
		value = {
			@ApiResponse(code = 200, message = "An updated Person", response = PersonDTO.class),
			@ApiResponse(code = 404, message = "Person not found") })
	@PutMapping(URL_ID)
	public PersonDTO update(@PathVariable long id, @RequestBody PersonDTO personDTO) {
		try {
			personDTO.setId(id);
			return personService.update(personDTO);
		} catch (FileNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Deletes an existing Person")
	@ApiResponses(
		value = {
			@ApiResponse(code = 200, message = "An updated Person", response = PersonDTO.class),
			@ApiResponse(code = 404, message = "Person not found") })
	@DeleteMapping(URL_ID)
	public void delete(@PathVariable long id) {
		personService.delete(id);
	}

	@Autowired
	private PersonService personService;

	public static final String URL = "/persons";

	public static final String URL_ID = "/{id}";
}
