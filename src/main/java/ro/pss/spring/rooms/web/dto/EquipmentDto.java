package ro.pss.spring.rooms.web.dto;

import lombok.NoArgsConstructor;
import ro.pss.spring.rooms.model.Equipment;

@NoArgsConstructor
public class EquipmentDto {
	public String type;
	public String serialNumber;

	public EquipmentDto(String type, String sn){
		this.type = type;
		this.serialNumber = sn;
	}

	public EquipmentDto(Equipment equipment) {
		this.type = equipment.getType().value();
		this.serialNumber = equipment.getSerialNumber();
	}
}
