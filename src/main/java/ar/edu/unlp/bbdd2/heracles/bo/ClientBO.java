/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface ClientBO {
	
	/**
	 * Inicia (start) el ejercicio que continua en la actividad actual del cliente
	 * 
	 * @param client
	 * 		Cliente
	 */
	void startExercise(Client client, ExerciseConfiguration exercise) throws BusinessException;
	
	/**
	 * Detiene (stop) el ejercicio que actualmente esta haciendo el cliente.
	 * s
	 * @param client
	 */
	void stopExercise (Client client) throws BusinessException;
	
	/**
	 * Cancela el ejercicio que se esta haciendo actualmente.
	 * 
	 * @param client
	 * 		Cliente
	 * @param sets
	 * 		Series que se lograron hacer
	 * @param reps
	 * 		Repeticiones de cada serie que se lograron hacer
	 * @param weight
	 * 		Peso con el que se realizo el ejercicio.
	 */
	void calcelExercise (Client client, List<Integer> sets, List<Integer> reps, Integer weight) throws BusinessException;

}
