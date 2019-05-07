package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ro.pss.spring.rooms.service.RoomService;
import ro.pss.spring.rooms.web.dto.RoomDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	private RoomService service;

	@GetMapping("{id}")
	public RoomDto getRoomById(@PathVariable Long id) {
		log.info("Getting room: {}", id);
		return new RoomDto(service.getRoom(id));
	}

	@PostMapping
	public void addRoom(@RequestBody RoomDto room) {
		log.info("Adding room: {}", room);
		service.createRoom(room);
	}

	@PutMapping("{id}")
	public void editRoom(@PathVariable Long id, @RequestBody RoomDto room) {
		log.info("Updating room: {} with {}", id, room);
		service.updateRoom(room);
	}

	@DeleteMapping("{id}")
	public void deleteRoom(@PathVariable Long id) {
		log.info("Deleting room: {} ", id);
		service.deleteRoom(id);
	}

	@GetMapping
	public List<RoomDto> search(@RequestParam(required = false, name = "id") Long idPart,
								@RequestParam(required = false, name = "name") String namePart,
								@RequestParam(required = false, name = "floor") String floorPart,
								@RequestParam(required = false, name = "availableSeats") Integer availableSeatsPart,
								@RequestParam(required = false, name = "equipmentType") String equipmentTypePart){
		return service.search(idPart, namePart, floorPart, availableSeatsPart, equipmentTypePart)
				.stream()
				.map(RoomDto::new)
				.collect(toList());
	}

	@GetMapping("/availability")
	public List<RoomDto> searchAvailability(@RequestParam(required = false, name = "id") Long idPart,
											@RequestParam(required = false, name = "name") String namePart,
											@RequestParam(required = false, name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePart,
											@RequestParam(required = false, name = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime fromPart,
											@RequestParam(required = false, name = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime toPart){
		return service.searchAvailability(idPart, namePart, datePart, fromPart, toPart)
				.stream()
				.map(RoomDto::new)
				.collect(toList());
	}
}
