package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ro.pss.spring.rooms.model.Reservation;
import ro.pss.spring.rooms.repo.ReservationRepository;
import ro.pss.spring.rooms.web.dto.ReservationDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ReservationService {
	@Autowired
	ReservationRepository repo;

}
