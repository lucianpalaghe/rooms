package ro.pss.spring.rooms.repo;

import org.springframework.stereotype.Repository;
import ro.pss.spring.rooms.model.Room;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class RoomRepository extends BaseRepository<Room> {
	public List<Room> findByName(String name){
		return map.values().stream().filter(r -> r.getName().contains(name)).collect(toList());
	}
}
