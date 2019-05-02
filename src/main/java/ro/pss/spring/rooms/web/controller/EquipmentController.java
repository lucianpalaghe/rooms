package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.pss.spring.rooms.service.EquipmentService;
import ro.pss.spring.rooms.web.dto.EquipmentDto;

import java.util.List;

@Slf4j
@RestController()
@RequestMapping("/api/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService service;

	@GetMapping()
	public List<EquipmentDto> getAllEquipment(){
		log.info("Getting all equipment...");
		return service.getAllEquipment();
	}

	@GetMapping("{id}")
	public EquipmentDto getEquipmentById(@PathVariable Long id){
		log.info("Getting equipment: {}", id);
		return service.getEquipment(id);
	}
}
