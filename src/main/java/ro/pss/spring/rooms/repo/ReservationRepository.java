package ro.pss.spring.rooms.repo;

import ro.pss.spring.rooms.model.RoomReservation;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ReservationRepository extends BaseRepository<RoomReservation> {
	public List<RoomReservation> findByRoomId(Long roomId){
		return map.values().stream().filter(r -> r.getRoomId() == roomId).collect(toList());
	}
}
