/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;

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
	
	/**
	 * Crea un nuevo cliente y lo retorna
	 * 
	 * @param name
	 * 		Nombre del cliente
	 * @param email
	 * 		Email del cliente
	 * @param birthday
	 * 		cumpleaños/fecha de nacimiento
	 * @param gender
	 * 		Genero
	 * @return
	 * 		Un entrenador (Client)
	 * @throws BusinessException
	 */
	public Client createClient (String name, String email, Date birthday, Gender gender) throws BusinessException;

}
