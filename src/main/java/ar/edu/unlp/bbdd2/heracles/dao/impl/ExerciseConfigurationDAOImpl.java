package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

public class ExerciseConfigurationDAOImpl extends BaseDAOImpl<ExerciseConfiguration> {
	
	public ExerciseConfigurationDAOImpl (Class<ExerciseConfiguration> classType){
		super();
		this.type = classType;
	}

}
