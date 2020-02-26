/**
 * 
 */
package foo.bar.data.dto;

/**
 */
public class PersonDTO {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return getFirstName() + ' ' + getLastName();
	}

	private Long id;

	private String firstName;

	private String lastName;
}
