package io.resi.patch.beans;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 *
 * JsonInclude.NON_NULL required to fulfill this pathway:
 *  Client side bean var = Optional.empty()
 *    -> Serialized to Json { "var":null }
 *      -> Server Side Bean var = Optional.empty()
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // null means un-set
public class Bean {
	private Optional<@NotNull @Size(min = 3, max = 10) String> firstName;
	private Optional<String> lastName;

	public Optional<String> getFirstName() {
		return firstName;
	}

	@JsonSetter
	public void setFirstName(final Optional<String> firstName) {
		this.firstName = firstName;
	}

	public Optional<String> getLastName() {
		return lastName;
	}

	@JsonSetter
	public void setLastName(final Optional<String> lastName) {
		this.lastName = lastName;
	}

	/**
	 * Convenience methods (not required)
	 */
	public void setFirstName(final String firstName) {
		this.firstName = Optional.of(firstName);
	}

	public void setLastName(final String lastName) {
		this.lastName = Optional.of(lastName);
	}
}
