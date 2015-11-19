package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.Exercise;

public interface ExerciseDAO extends BaseDAO<Exercise> {

	/**
	 * Busca y retorna un ejercicio por nombre
	 * 
	 * @param name
	 *            del ejercicio
	 * @return
	 */
	public Exercise findByName(String name);
}
