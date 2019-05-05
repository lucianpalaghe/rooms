package ro.pss.spring.rooms.repo;

import org.springframework.stereotype.Repository;
import ro.pss.spring.rooms.model.Reservation;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class ReservationRepository extends BaseRepository<Reservation> {
	public List<Reservation> findByRoomId(Long roomId){
		return map.values().stream().filter(r -> r.getRoomId() == roomId).collect(toList());
	}
}
