package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.pss.spring.rooms.service.ReservationService;
import ro.pss.spring.rooms.web.dto.ReservationDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	@Autowired
	ReservationService service;

	@GetMapping
	public List<ReservationDto> search(@RequestParam(value = "roomId") Long roomIdPart){
		if(roomIdPart != null){
			log.info("Getting reservations for room: {}", roomIdPart);
			return service.getReservationByRoomId(roomIdPart).stream().map(ReservationDto::new).collect(toList());
		}

		log.info("Getting all reservations");
		return service.getAllReservations().stream().map(ReservationDto::new).collect(toList());
	}

	@GetMapping("{id}")
	public ReservationDto getReservationById(@PathVariable Long id){
		log.info("Getting reservation: {}", id);
		return new ReservationDto(service.getReservation(id));
	}

	@PostMapping
	public void addReservation(@RequestBody ReservationDto reservation){
		log.info("Adding reservation: {}", reservation);
		service.createReservation(reservation);
	}

	@PutMapping("{id}")
	public void editReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto){
		log.info("Updating reservation: {} with {}", id, reservationDto);
		service.updateReservation(reservationDto);
	}

	@DeleteMapping("{id}")
	public void deleteReservation(@PathVariable Long id){
		log.info("Deleting reservation: {} ", id);
		service.deleteReservation(id);
	}
}
