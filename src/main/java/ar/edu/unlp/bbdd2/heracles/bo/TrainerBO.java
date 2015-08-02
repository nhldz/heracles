/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface TrainerBO {
	
	/**
	 * Asigna una rutina a un cliente, si la rutina no tiene un cliente.
	 * La rutina es asignada al cliente como rutina actual.
	 * @param client
	 * @param routine
	 * @throws BusinessException 
	 */
	void assingRoutine (Client client, Routine routine) throws BusinessException;
	
	/**
	 * Le asigna la copia de una rutina a un cliente.
	 * 
	 * @param client
	 * @param routine
	 */
	void copyRoutine (Client client, Routine routine) throws BusinessException;
	
	/**
	 * Pone en estado omitidio (skip) al ejercicio 
	 * que deberia hacerce actialmente por el usuario.
	 * 
	 * @param client
	 */
	void skipExercise (Client client) throws BusinessException;

}
