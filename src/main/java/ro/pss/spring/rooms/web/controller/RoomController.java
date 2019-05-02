package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.pss.spring.rooms.service.RoomService;
import ro.pss.spring.rooms.web.dto.RoomDto;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private RoomService service;

    @GetMapping()
    public List<RoomDto> getAllRooms(){
        log.info("Getting all rooms...");
        return service.getAllRooms();
    }

    @GetMapping("{id}")
    public RoomDto getRoomById(@PathVariable Long id){
        log.info("Getting room: {}", id);
        return service.getRoom(id);
    }

    @PostMapping
    public void addRoom(@RequestBody RoomDto room){
        log.info("Adding room: {}", room);
        service.createRoom(room);
    }

    @PutMapping("{id}")
    public void editRoom(@PathVariable Long id, @RequestBody RoomDto room){
        log.info("Updating room: {} with {}", id, room);
        service.updateRoom(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id){
        log.info("Deleting room: {} ", id);
        service.deleteRoom(id);
    }
}
