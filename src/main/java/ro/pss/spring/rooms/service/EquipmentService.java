package ro.pss.spring.rooms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.pss.spring.rooms.model.Equipment;
import ro.pss.spring.rooms.model.EquipmentType;
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

	public void createEquipment(EquipmentDto equipmentDto) {
		Equipment r = fill(equipmentDto);
		repo.save(r);
	}
	// TODO change this

	public void updateEquipment(EquipmentDto equipmentDto) {
		Equipment r = fill(equipmentDto);
		repo.save(r);
	}

	private Equipment fill(EquipmentDto dto) {
		Equipment targetEntity = new Equipment();
		targetEntity.setType(EquipmentType.fromValue(dto.type));
		targetEntity.setSerialNumber(dto.serialNumber);

		return targetEntity;
	}
}
