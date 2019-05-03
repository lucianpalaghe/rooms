package ro.pss.spring.rooms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RoomReservation extends BaseEntity {
	private Long roomId;
	private LocalDate from;
	private LocalDate to;
}
