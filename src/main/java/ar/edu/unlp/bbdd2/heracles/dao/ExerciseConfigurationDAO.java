package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;

public interface ExerciseConfigurationDAO extends BaseDAO<ExerciseConfiguration> {
	
	/**
	 * Retorna el ultimo snapshot del ejercicio.
	 * 
	 * @param exerciseConfiguration
	 * @return
	 * 		Snapshot del ejercicio
	 */
	public ExerciseSnapshot lastSnapshot (ExerciseConfiguration exerciseConfiguration); 

}
