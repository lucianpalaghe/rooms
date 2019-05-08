package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Equipment;

@NoArgsConstructor
public class EquipmentDto {
	public Long id;
	public String type;
	public String serialNumber;

	public EquipmentDto(Equipment equipment) {
		this.id = equipment.getId();
		this.type = equipment.getType().value();
		this.serialNumber = equipment.getSerialNumber();
	}
}
