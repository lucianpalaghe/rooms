package ro.pss.spring.rooms.repo;

import org.springframework.stereotype.Repository;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.EquipmentType;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class EquipmentRepository extends BaseRepository<Equipment> {
	public List<Equipment> findBySerialNumber(String sn){
		return map.values().stream().filter(e -> e.getSerialNumber().contains(sn)).collect(toList());
	}

	public List<Equipment> findByEquipmentType(String eqType){
		EquipmentType type = EquipmentType.fromString(eqType);
		return findByEquipmentType(type);
	}

	public List<Equipment> findByEquipmentType(EquipmentType eqType){
		return map.values().stream().filter(e -> e.getType().equals(eqType)).collect(toList());
	}
}

