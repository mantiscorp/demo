/**
 * 
 */
package foo.bar.service;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import foo.bar.data.converters.PersonConverter;
import foo.bar.data.dto.PersonDTO;
import foo.bar.data.model.Person;
import foo.bar.data.repository.PersonRepository;

/**
 * @author pdmitry
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

	@Transactional(readOnly = true)
	@Override	
	public Collection<PersonDTO> getAll() {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false).map(personConverter::convert)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public PersonDTO getById(long id) throws FileNotFoundException {
		return personConverter.convert(findById(id));
	}

	@Override
	public PersonDTO save(PersonDTO personDTO) {
		return save(personConverter.convert(personDTO));
	}

	@Override
	public PersonDTO update(PersonDTO personDTO) throws FileNotFoundException {
		Person person = findById(personDTO.getId());
		personConverter.convert(personDTO, person);
		return save(person);
	}

	@Override
	public void delete(long id) {
		personRepository.deleteById(id);
	}

	private Person findById(long id) throws FileNotFoundException {
		return personRepository.findById(id).orElseThrow(FileNotFoundException::new);
	}
	
	private PersonDTO save(Person person) {
		return personConverter.convert(personRepository.save(person));
	}

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonConverter personConverter;
}
