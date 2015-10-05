package ar.edu.unlp.bbdd2.heracles.dao.impl;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import ar.edu.unlp.bbdd2.heracles.dao.BaseDAO;

import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
	
	protected Class<T> type;
	
	public void setType (Class<T> classType){
		this.type = classType;
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
		ofy().save().entity(object).now();
	}

	@Override
	public void saveAll(Collection<T> objects) {
		ofy().save().entities(objects).now();		
	}

	@Override
	public void saveOrUpdate(T object) {
		ofy().save().entity(object).now();		
	}

	@Override
	public void saveOrUpdateAll(Collection<T> objects) {
		ofy().save().entities(objects).now();		
		
	}
	
	@Override
	public T load(T object) {
		LoadResult<T> resutl = ofy().load().entity(object);
		
		return resutl.now();
	}

	@Override
	public T loadById(Long id) {
		return ofy().load().type(this.getType()).id(id).now();
	}

	@Override
	public Collection<T> loadAll(Collection<T> objects) {
		List<T> listResult = ofy().load().type(this.getType()).list();
		return listResult;
	}
	
	@Override
	public Collection<T> loadAllById(Collection<Long> ids) {
		Map<Long, T> mapResult = ofy().load().type(this.getType()).ids(ids);
		return mapResult.values();
	}
	
	public List<T> loadAll (){
		return (List<T>) ofy().load().type(this.getType()).list();
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
