package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
		Room r = fill(room);
		repo.save(r);
	}
	// TODO change this
	public void updateRoom(RoomDto room) {
		Room r = fill(room);
		repo.save(r);
	}

	public void deleteRoom(Long id) {
		repo.deleteById(id);
	}

	private Room fill(RoomDto dto) {
		Room targetEntity = new Room();
		targetEntity.setName(dto.name);
		targetEntity.setAvailableSeats(dto.availableSeats);
		targetEntity.setFloor(dto.floor);

		return targetEntity;
	}
}
