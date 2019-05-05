package ro.pss.spring.rooms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Reservation extends BaseEntity {
	private Long roomId;
	private LocalDate date;
	private LocalTime from;
	private LocalTime to;

	public Reservation(Long roomId, LocalDate date, LocalTime from, LocalTime to) {
		this.roomId = roomId;
		this.date = date;
		this.from = from;
		this.to = to;
	}
}
