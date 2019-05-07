package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Participant;

@NoArgsConstructor
public class ParticipantDto {
	public Long id;
	private String name;
	private String surname;
	private String employeeId;

	public ParticipantDto(Participant participant){
		this.id = participant.getId();
		this.name = participant.getName();
		this.surname = participant.getSurname();
		this.employeeId = participant.getEmployeeId();
	}
}
