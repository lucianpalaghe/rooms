package ro.pss.spring.rooms.model;

public enum EquipmentType {
	TV("TV"),
	PHONE("PHONE"),
	PROJECTOR("PROJECTOR"),
	AIR_CONDITIONING("AIR_CONDITIONING");

	private final String value;

	EquipmentType(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	public static EquipmentType fromString(String type) {
		for (EquipmentType c: EquipmentType.values()) {
			if (c.value.equalsIgnoreCase(type)) {
				return c;
			}
		}
		throw new IllegalArgumentException(type);
	}
}
