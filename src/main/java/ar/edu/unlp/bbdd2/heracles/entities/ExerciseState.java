/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.entities;

/**
 * Indica los estados en lo que se encuentra el ejercicio.
 * 
 * 
 * 
 * 
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public enum ExerciseState {
	
	/**
	 * RUN: en ejecucción.
	 */
	RUN,
	
	/**
	 * CANCEL: cancelado o termiando de manera inesperada.
	 */
	CANCEL, 
	
	/**
	 * SKIPT: se salteo el ejercicio por algún motivo indicado por el entrenador.
	 */
	SKIP, 
	
	/**
	 * STOP: se termino de manera correcta.
	 */
	STOP;
	
}
