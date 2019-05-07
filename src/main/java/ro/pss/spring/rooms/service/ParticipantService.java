package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Participant;
import ro.pss.spring.rooms.repo.ParticipantRepository;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class ParticipantService {
	@Autowired
	private ParticipantRepository repo;

	public Participant getParticipantById(Long id){
		return repo.getById(id);
	}

	public List<Participant> search(String namePart, String surnamePart, String employeeId){
		Stream<Participant> stream = repo.findAll().stream();

		if(namePart != null){
			stream.filter(r -> r.getName().contains(namePart));
		}

		if(surnamePart != null){
			stream.filter(r -> r.getSurname().contains(surnamePart));
		}

		if(employeeId != null){
			stream.filter(r -> r.getEmployeeId().contains(employeeId));
		}

		return stream.collect(toList());
	}
}
