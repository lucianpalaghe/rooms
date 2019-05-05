package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.repo.EquipmentRepository;
import ro.pss.spring.rooms.web.dto.EquipmentDto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EquipmentService {
	@Autowired
	private EquipmentRepository repo;

	public EquipmentDto getEquipment(Long id) {
		return new EquipmentDto(repo.getById(id));
	}

	public List<EquipmentDto> getAllEquipment(){
		return repo.findAll().stream().map(EquipmentDto::new).collect(toList());
	}
}
