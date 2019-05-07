package ro.pss.spring.rooms.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.Reservation;
import ro.pss.spring.rooms.model.Room;
import ro.pss.spring.rooms.repo.EquipmentRepository;
import ro.pss.spring.rooms.repo.ReservationRepository;
import ro.pss.spring.rooms.repo.RoomRepository;
import ro.pss.spring.rooms.web.dto.RoomDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class RoomService {
	@Autowired
	private RoomRepository repo;

	@Autowired
	private EquipmentRepository equipmentRepo;

	@Autowired
	private ReservationRepository reservationRepo;

	public Room getRoom(Long id) {
		return repo.getById(id);
	}

	public List<Room> getAllRooms(){
		return repo.findAll();
	}

	public void createRoom(RoomDto room) {
		Room r = new Room();
		fill(r, room);
		repo.save(r);
	}

	public void updateRoom(RoomDto room) {
		Room r = repo.getById(room.id);
		fill(r, room);
	}

	public void deleteRoom(Long id) {
		repo.deleteById(id);
	}

	private void fill(Room r, RoomDto dto) {
		validateReservation(dto);
		r.setName(dto.name);
		r.setAvailableSeats(dto.availableSeats);
		r.setFloor(dto.floor);
		r.setEquipment(dto.equipment.stream().map(e -> new Equipment(e.type, e.serialNumber)).collect(toList()));
	}

	private void validateReservation(RoomDto r){
		if (r.name == null || r.name.isEmpty()) {
			throw new IllegalArgumentException("error.room.nameMandatory");
		}

		if (r.availableSeats == null || r.availableSeats == 0) {
			throw new IllegalArgumentException("error.room.availableSeatsMandatory");
		}

		if (r.availableSeats > 1000) {
			throw new IllegalArgumentException("error.room.tooManyAvailableSeats");
		}

		if (r.floor == null || r.floor.isEmpty()) {
			throw new IllegalArgumentException("error.room.floorMandatory");
		}

		if (r.equipment == null || r.equipment.size() == 0) {
			throw new IllegalArgumentException("error.room.atLeastOneEquipmentMandatory");
		}

		// TODO equipment type exists validations
	}

	public List<Room> search(Long idPart, String namePart, String floorPart, Integer availableSeatsPart, String equipmentTypePart){
		Stream<Room> stream = repo.findAll().stream();

		if(idPart != null){
			stream = stream.filter(r -> r.getId().equals(idPart)).collect(toList()).stream();
		}

		if(namePart != null){
			stream = stream.filter(r -> StringUtils.containsIgnoreCase(r.getName(), namePart)).collect(toList()).stream();
		}

		if(floorPart != null){
			stream = stream.filter(r -> r.getFloor().equalsIgnoreCase(floorPart)).collect(toList()).stream();
		}

		if(availableSeatsPart != null){
			stream = stream.filter(r -> r.getAvailableSeats().equals(availableSeatsPart)).collect(toList()).stream();
		}

		if(equipmentTypePart != null){
			List<Equipment> equipmentList = equipmentRepo.findByEquipmentType(equipmentTypePart);
			stream = stream.filter(r -> !r.getEquipment().containsAll(equipmentList)).collect(toList()).stream();
		}

		return stream.collect(toList());
	}

	public List<Room> searchAvailability(Long idPart, String namePart, LocalDate datePart, LocalTime fromPart, LocalTime toPart) {
		List<Room> roomList = search(idPart, namePart, null, null, null);

		Stream<Reservation> reservationStream = reservationRepo.findAll().stream();

		if(datePart != null) {
			reservationStream = reservationStream.filter(r -> r.getDate().equals(datePart)).collect(toList()).stream();
		}

		if(fromPart == null){
			fromPart = LocalTime.of(0, 0);
		}

		if(toPart == null){
			toPart = LocalTime.of(12, 59);
		}

		LocalTime finalFromPart = fromPart;
		LocalTime finalToPart = toPart;
		List<Long> idList = reservationStream.filter(r -> r.isDateIntervalOverlapping(finalFromPart, finalToPart)).map(Reservation::getRoomId).collect(toList());

		final List<Room> collect = roomList.stream().filter(r -> (!idList.contains(r.getId()))).collect(toList());
		return collect;
	}
}
