package ar.edu.unlp.bbdd2.heracles.dao.impl;


import java.util.Collection;
import java.util.Map;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

import ar.edu.unlp.bbdd2.heracles.dao.BaseDAO; 

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	
	private Class<T> type;
	
	public BaseDAOImpl (Class<T> type){
		this.type = type;
	}
	
	public Class<T> getType() {
		return this.type;
	}
	
	public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }

	@Override
	public void save(T object) {
		ofy().save().entity(object);
	}

	@Override
	public void saveAll(Collection<T> objects) {
		ofy().save().entities(objects);		
	}

	@Override
	public void saveOrUpdate(T object) {
		ofy().save().entity(object);		
	}

	@Override
	public void saveOrUpdateAll(Collection<T> objects) {
		ofy().save().entities(objects);		
		
	}
	
	@Override
	public T load(T object) {
		return (T) ofy().load().entity(object);
	}

	@Override
	public T loadById(Long id) {
		return ofy().load().type(this.getType()).id(id).now();
	}

	@Override
	public Collection<T> loadAll(Collection<T> objects) {
		Map<T, T> mapResult = (Map<T, T>) ofy().load().entities(objects);
		return mapResult.values();
	}
	
	@Override
	public Collection<T> loadAllById(Collection<Long> ids) {
		Map<Long, T> mapResult = ofy().load().type(this.getType()).ids(ids);
		return mapResult.values();
	}

	@Override
	public void delete(T object) {
		ofy().delete().entity(object);
	}
	
	@Override
	public void deleteById(Long id) {
		ofy().delete().type(this.getType()).id(id);
	}

	@Override
	public void deleteAll(Collection<T> objects) {
		ofy().delete().entities(objects);
	}
	
	@Override
	public void deleteAllByIds(Collection<Long> ids) {
		ofy().delete().type(this.getType()).ids(ids);
	}

}
