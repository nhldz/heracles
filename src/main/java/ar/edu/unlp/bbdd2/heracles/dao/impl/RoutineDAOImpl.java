package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.Routine;

public class RoutineDAOImpl extends BaseDAOImpl<Routine> {
	
	public RoutineDAOImpl (Class<Routine> classType){
		super();
		this.type = classType;
	}

}
