package ro.pss.spring.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.EquipmentType;
import ro.pss.spring.rooms.model.Room;
import ro.pss.spring.rooms.repo.EquipmentRepository;
import ro.pss.spring.rooms.repo.RoomRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class DummyData {
	@Autowired
	RoomRepository roomRepo;

	@Autowired
	EquipmentRepository equipmentRepo;

	@PostConstruct
	public void buildDummyData(){
		Equipment e1 = new Equipment(EquipmentType.TV, "SN1");
		Equipment e2 = new Equipment(EquipmentType.PHONE, "SN2");
		Equipment e3 = new Equipment(EquipmentType.AIR_CONDITIONING, "SN3");
		Equipment e4 = new Equipment(EquipmentType.PROJECTOR, "SN4");
		Equipment e5 = new Equipment(EquipmentType.TV, "SN5");
		equipmentRepo.save(e1);
		equipmentRepo.save(e2);
		equipmentRepo.save(e3);
		equipmentRepo.save(e4);
		equipmentRepo.save(e5);
		List<Equipment> equipmentListFull = Arrays.asList(e1, e2, e3, e4, e5);
		List<Equipment> equipmentListMini = Arrays.asList(e2, e3);

		Room r1 = new Room("Sala de sedinte", 14, "4a", equipmentListFull);
		Room r2 = new Room("Sala dezvoltare", 35, "4a", equipmentListMini);
		Room r3 = new Room("Sala testare", 20, "4a", equipmentListFull);
		Room r4 = new Room("Biroul administrativ", 5, "5", equipmentListMini);

		roomRepo.save(r1);
		roomRepo.save(r2);
		roomRepo.save(r3);
		roomRepo.save(r4);
	}
}