package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;

public class ExerciseSnapshotDAOImpl extends BaseDAOImpl<ExerciseSnapshot> {
	
	public ExerciseSnapshotDAOImpl (Class<ExerciseSnapshot> classType){
		super();
		this.type = classType;
	}

}
