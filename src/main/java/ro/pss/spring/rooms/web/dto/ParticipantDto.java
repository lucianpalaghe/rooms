package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Participant;

@NoArgsConstructor
public class ParticipantDto {
	public Long id;
	public String name;
	public String surname;
	public String employeeId;

	public ParticipantDto(Participant participant){
		this.id = participant.getId();
		this.name = participant.getName();
		this.surname = participant.getSurname();
		this.employeeId = participant.getEmployeeId();
	}
}
