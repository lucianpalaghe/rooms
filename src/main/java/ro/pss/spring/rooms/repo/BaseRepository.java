package ro.pss.spring.rooms.repo;

import ro.pss.spring.rooms.model.BaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class BaseRepository<T extends BaseEntity> {
	protected HashMap<Long, T> map = new LinkedHashMap<>();
	private long nextId = 0;

	public T getById(Long id){
		if(!map.containsKey(id)){
			throw new IllegalArgumentException("Entity with id not found(" + id + ")");
		}
		return map.get(id);
	}

	public void save(T entity){
		entity.setId(++nextId);
		map.put(entity.getId(), entity);
	}

	public void deleteById(Long id){
		if(!map.containsKey(id)){
			throw new IllegalArgumentException("Entity with id not found(" + id + ")");
		}
		map.remove(id);
	}

	public List<T> findAll(){
		return new ArrayList<T>(map.values());
	}
}
