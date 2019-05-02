package ro.pss.spring.rooms.repo;

import org.springframework.stereotype.Repository;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.Room;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class EquipmentRepository extends BaseRepository<Equipment> {
	public List<Equipment> findBySerialNumber(String sn){
		return map.values().stream().filter(e -> e.getSerialNumber().contains(sn)).collect(toList());
	}
}

