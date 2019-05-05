package ro.pss.spring.rooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.EquipmentType;
import ro.pss.spring.rooms.model.Reservation;
import ro.pss.spring.rooms.model.Room;
import ro.pss.spring.rooms.repo.EquipmentRepository;
import ro.pss.spring.rooms.repo.ReservationRepository;
import ro.pss.spring.rooms.repo.RoomRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Service
public class DummyData {
	@Autowired
	RoomRepository roomRepo;

	@Autowired
	EquipmentRepository equipmentRepo;

	@Autowired
	ReservationRepository reservationRepo;

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

		LocalDate d1 = LocalDate.of(2019, 5, 10);
		LocalTime t1 = LocalTime.of(9, 0);
		LocalTime t2 = LocalTime.of(12, 0);
		LocalTime t3 = LocalTime.of(15, 0);
		LocalTime t4 = LocalTime.of(16, 0);

		roomRepo.save(r1);
		roomRepo.save(r2);
		roomRepo.save(r3);
		roomRepo.save(r4);

		Reservation res1a = new Reservation(1L, d1, t1, t2);
		Reservation res1b = new Reservation(1L, d1, t2, t3);
		Reservation res1c = new Reservation(1L, d1, t3, t4);
		Reservation res2 = new Reservation(2L, d1, t2, t3);

		reservationRepo.save(res1a);
		reservationRepo.save(res1b);
		reservationRepo.save(res1c);
		reservationRepo.save(res2);

	}
}