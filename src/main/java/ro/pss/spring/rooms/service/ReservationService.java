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

	public List<Reservation> getAllReservations(){
		return repo.findAll();
	}

	public Reservation getReservation(@PathVariable Long id){
		return repo.getById(id);
	}

	public List<Reservation> getReservationByRoomId(@PathVariable Long roomId){
		return repo.findByRoomId(roomId);
	}

	public void createReservation(ReservationDto room) {
		Reservation r = new Reservation();
		fill(r, room);
		repo.save(r);
	}

	public void updateReservation(ReservationDto room) {
		Reservation r = repo.getById(room.id);
		fill(r, room);
	}

	public void deleteReservation(Long id) {
		repo.deleteById(id);
	}

	private void fill(Reservation r, ReservationDto dto) {
		validateReservation(dto);
		r.setRoomId(dto.roomId);
		r.setDate(dto.date);
		r.setFrom(dto.from);
		r.setTo(dto.to);
	}

	private void validateReservation(ReservationDto r){
		if (r.roomId == null) {
			throw new IllegalArgumentException("error.reservation.roomIdMandatory");
		}
		if (r.date == null) {
			throw new IllegalArgumentException("error.reservation.dateMandatory");
		}

		if (r.from == null) {
			throw new IllegalArgumentException("error.reservation.fromMandatory");
		}

		if (r.to == null) {
			throw new IllegalArgumentException("error.reservation.toMandatory");
		}

		if (r.to.isBefore(r.from)) {
			throw new IllegalArgumentException("error.reservation.toBeforeFrom");
		}

		List<Reservation> resList = repo.findByRoomId(r.roomId).stream().filter(room -> r.date.isEqual(room.getDate())).collect(toList());
		for(Reservation res: resList){
			if (res.isDateIntervalOverlapping(r.from, r.to)) {
				throw new IllegalArgumentException("error.interval.overlapping");
			}
		}
	}
}
