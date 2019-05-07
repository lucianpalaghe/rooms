package ro.pss.spring.rooms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Participant extends BaseEntity {
	private String name;
	private String surname;
	private String employeeId;

	public Participant(String name, String surname, String employeeId) {
		this.name = name;
		this.surname = surname;
		this.employeeId = employeeId;
	}
}
