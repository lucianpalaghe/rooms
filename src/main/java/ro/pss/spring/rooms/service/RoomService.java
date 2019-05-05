package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.Room;
import ro.pss.spring.rooms.repo.RoomRepository;
import ro.pss.spring.rooms.web.dto.RoomDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RoomService {
	@Autowired
	private RoomRepository repo;

	public RoomDto getRoom(Long id) {
		return new RoomDto(repo.getById(id));
	}

	public List<RoomDto> getAllRooms(){
		return repo.findAll().stream().map(RoomDto::new).collect(toList());
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
		r.setName(dto.name);
		r.setAvailableSeats(dto.availableSeats);
		r.setFloor(dto.floor);
		r.setEquipment(dto.equipment.stream().map(e -> new Equipment(e.type, e.serialNumber)).collect(toList()));
	}
}
