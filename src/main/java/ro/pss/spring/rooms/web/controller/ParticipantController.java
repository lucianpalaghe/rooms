package ro.pss.spring.rooms.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.pss.spring.rooms.model.Participant;
import ro.pss.spring.rooms.service.ParticipantService;
import ro.pss.spring.rooms.web.dto.ParticipantDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
	@Autowired
	private ParticipantService service;

	@GetMapping("{id}")
	public ParticipantDto getParticipantById(@PathVariable Long id) {
		log.info("Getting participant: {}", id);
		return new ParticipantDto(service.getParticipantById(id));
	}

//	@PostMapping
//	public void addParticipant(@RequestBody ParticipantDto participant) {
//		log.info("Adding participant: {}", participant);
//		facade.createParticipant(participant);
//	}
//
//	@PutMapping("{id}")
//	public void editParticipant(@PathVariable Long id, @RequestBody ParticipantDto participant) {
//		log.info("Updating participant: {} with {}", id, participant);
//		facade.updateParticipant(participant);
//	}
//
//	@DeleteMapping("{id}")
//	public void deleteParticipant(@PathVariable Long id) {
//		log.info("Deleting participant: {} ", id);
//		facade.deleteParticipant(id);
//	}

	@GetMapping
	public List<ParticipantDto> search(@RequestParam(required = false, name = "name") String namePart,
									   @RequestParam(required = false, name = "surname") String surnamePart,
									   @RequestParam(required = false, name = "employeeId") String employeePart){
		List<Participant> list = service.search(namePart, surnamePart, employeePart);
		System.out.println(list);
		return service.search(namePart, surnamePart, employeePart)
				.stream()
				.map(ParticipantDto::new)
				.collect(toList());
	}
}
