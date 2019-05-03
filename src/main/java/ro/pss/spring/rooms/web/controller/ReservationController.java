package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.pss.spring.rooms.model.RoomReservation;
import ro.pss.spring.rooms.service.ReservationService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	@Autowired
	ReservationService service;

	@GetMapping
	public List<RoomReservation> getAllReservations(){
		log.info("Getting all reservations");
		return service.getAllReservations();
	}

	@GetMapping("{id}")
	public RoomReservation getReservationById(@PathVariable Long id){
		log.info("Getting reservation {}", id);
		return service.getReservation(id);
	}

}
