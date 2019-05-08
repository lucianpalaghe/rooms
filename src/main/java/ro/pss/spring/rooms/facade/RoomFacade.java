package ro.pss.spring.rooms.facade;

import org.springframework.beans.factory.annotation.Autowired;
import ro.pss.spring.rooms.model.Room;
import ro.pss.spring.rooms.service.RoomService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Facade
public class RoomFacade {
	@Autowired
	private RoomService service;

	public List<Room> search(RoomCriteria criteria) {
		return service.search(criteria);
	}

	public List<Room> searchAvailability(RoomAvailabilityCriteria criteria) {
		return service.searchAvailability(criteria);
	}

}
