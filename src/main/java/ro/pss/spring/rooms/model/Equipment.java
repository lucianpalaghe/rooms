package ro.pss.spring.rooms.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Equipment extends BaseEntity {
	private EquipmentType type;
	private String serialNumber;

	public Equipment(String type, String serialNumber) {
		this.type = EquipmentType.fromValue(type);
		this.serialNumber = serialNumber;
	}

	public Equipment(EquipmentType type, String serialNumber) {
		this.type = type;
		this.serialNumber = serialNumber;
	}
}