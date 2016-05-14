package ar.edu.unlp.bbdd2.heracles.bo.impl;

import ar.edu.unlp.bbdd2.heracles.bo.ExerciseConfigurationBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

public class ExerciseConfigurationBOImpl implements ExerciseConfigurationBO{
	
	private ExerciseConfigurationDAOImpl exerciseConfigurationDAO;

	@Override
	public ExerciseConfiguration getExerciseConfiguration(Long id) {
		return this.getExerciseConfigurationDAO().loadById(id);
	}

	public ExerciseConfigurationDAOImpl getExerciseConfigurationDAO() {
		return exerciseConfigurationDAO;
	}

	public void setExerciseConfigurationDAO (ExerciseConfigurationDAOImpl exerciseConfigurationDAO) {
		this.exerciseConfigurationDAO = exerciseConfigurationDAO;
	}
	
}
