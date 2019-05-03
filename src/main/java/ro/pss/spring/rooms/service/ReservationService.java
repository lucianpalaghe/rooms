package ro.pss.spring.rooms.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ro.pss.spring.rooms.model.RoomReservation;
import ro.pss.spring.rooms.repo.ReservationRepository;

import java.util.List;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository repo;

	public List<RoomReservation> getAllReservations(){
		return repo.findAll();
	}

	public RoomReservation getReservation(@PathVariable Long id){
		return repo.getById(id);
	}
}
