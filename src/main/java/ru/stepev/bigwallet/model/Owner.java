package ru.stepev.bigwallet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

	private Integer id;
	private String firstName;
	private String lastName;

	public Owner(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
