package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Room;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class RoomDto {
	public Long id;
	public String name;
	public Integer availableSeats;
	public String floor;
	public List<EquipmentDto> equipment;

	public RoomDto(Room room){
		this.id = room.getId();
		this.name = room.getName();
		this.availableSeats = room.getAvailableSeats();
		this.floor = room.getFloor();
		this.equipment = room.getEquipment()
					.stream()
					.map(e -> new EquipmentDto(e.getType().value(), e.getSerialNumber())).collect(Collectors.toList());
	}
}
