package ro.pss.spring.rooms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Room extends BaseEntity {
	private String name;
	private Integer availableSeats;
	private String floor;
	private List<Equipment> equipment = new ArrayList<>();

	public Room(String name, Integer availableSeats, String floor, List<Equipment> equipmentList) {
		this.name = name;
		this.availableSeats = availableSeats;
		this.floor = floor;
		this.equipment = equipmentList;
	}
}
