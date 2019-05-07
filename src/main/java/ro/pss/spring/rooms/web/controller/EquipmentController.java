package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.pss.spring.rooms.service.EquipmentService;
import ro.pss.spring.rooms.web.dto.EquipmentDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController()
@RequestMapping("/api/equipment")
public class EquipmentController {
	@Autowired
	private EquipmentService service;

	@GetMapping("{id}")
	public EquipmentDto getEquipmentById(@PathVariable Long id){
		log.info("Getting equipment: {}", id);
		return service.getEquipment(id);
	}

	@GetMapping
	public List<EquipmentDto> search(@RequestParam(required = false, name = "type") String typePart,
									 @RequestParam(required = false, name = "serialNumber") String serialNumberPart){
		return service.search(typePart, serialNumberPart)
				.stream()
				.map(EquipmentDto::new)
				.collect(toList());
	}
}
