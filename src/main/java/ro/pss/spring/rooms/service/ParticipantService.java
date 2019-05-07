package ro.pss.spring.rooms.service;

import org.apache.commons.lang3.StringUtils;
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

	public List<Participant> search(String namePart, String surnamePart, String employeeIdPart){
		Stream<Participant> stream = repo.findAll().stream();

		return stream.filter(namePart == null ? r -> true : r -> StringUtils.containsIgnoreCase(r.getName(), namePart))
					 .filter(surnamePart == null ? r -> true : r -> StringUtils.containsIgnoreCase(r.getSurname(), surnamePart))
					 .filter(employeeIdPart == null ? r -> true : r -> StringUtils.containsIgnoreCase(r.getEmployeeId(), employeeIdPart))
					 .collect(toList());
	}
}
