package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;

/**
 * DAO de Actividad
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface ActivityDAO extends BaseDAO<Activity> {

	/**
	 * Retorna la cantidad de ejercicios de una actividad en determinado estado.
	 * 
	 * @param activity
	 *            La actividad de la que se quieren saber los ejercicios
	 * @param state
	 *            El estado del ejercicio.
	 * @return Entero con la cantidad de ejercicios en un estado en particular.
	 */
	public Integer excerciseStateCount(Activity activity, ExerciseState state);

}
