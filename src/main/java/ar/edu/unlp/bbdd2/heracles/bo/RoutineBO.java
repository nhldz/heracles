/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface RoutineBO {
	
	/**
	 * Retorna todas las actividades que a la fecha tienen los ejercicios completos.
	 * 
	 * @return
	 * 		Lista de actividades
	 */
	List <Activity> getCompleteActivities ();
	
	
	/**
	 * Retorna la actividad actual. 
	 * La actividad actual es la que se considera debería estar realizando el cliente.
	 * 
	 * @return
	 * 		Actividad
	 */
	Activity getActualActivity();
	
	/**
	 * Retorna aquellas actividades que contienen ejercicios cancelados.
	 * 
	 * @return
	 * 		Lista de actividades canceladas
	 */
	List<Activity> getCanceledActivities ();

}
