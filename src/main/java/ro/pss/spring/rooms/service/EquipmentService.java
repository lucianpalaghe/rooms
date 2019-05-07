package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.EquipmentType;
import ro.pss.spring.rooms.repo.EquipmentRepository;
import ro.pss.spring.rooms.web.dto.EquipmentDto;

import java.util.List;
import java.util.stream.Stream;

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

	public List<Equipment> search(String typePart, String serialNumberPart) {
		Stream<Equipment> stream = repo.findAll().stream();

		if(typePart != null){
			EquipmentType type = EquipmentType.fromString(typePart);
			stream.filter(r -> r.getType().equals(type));
		}

		if(serialNumberPart != null){
			stream.filter(r -> r.getSerialNumber().equalsIgnoreCase(serialNumberPart));
		}

		return stream.collect(toList());
	}
}
