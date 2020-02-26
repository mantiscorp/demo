/**
 * 
 */
package foo.bar.data.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import foo.bar.data.dto.PersonDTO;
import foo.bar.data.model.Person;

/**
 */
@Component
public class PersonConverter {

	@Autowired
	private ModelMapper modelMapper;

	public Person convert(PersonDTO personDTO) {
		return modelMapper.map(personDTO, Person.class);
	}

	public PersonDTO convert(Person person) {
		return modelMapper.map(person, PersonDTO.class);
	}
	
	public void convert(PersonDTO personDTO, Person person) {
		modelMapper.map(personDTO, person);
	}
}
