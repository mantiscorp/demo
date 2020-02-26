/**
 * 
 */
package foo.bar.service;

import java.io.FileNotFoundException;
import java.util.Collection;

import foo.bar.data.dto.PersonDTO;

/**
 */
public interface PersonService {

	public Collection<PersonDTO> getAll();

	public PersonDTO getById(long id) throws FileNotFoundException;

	public PersonDTO save(PersonDTO personDTO);

	public PersonDTO update(PersonDTO personDTO) throws FileNotFoundException;

	public void delete(long id);
}
