/**
 * 
 */
package foo.bar.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import foo.bar.data.model.Person;

/**
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
