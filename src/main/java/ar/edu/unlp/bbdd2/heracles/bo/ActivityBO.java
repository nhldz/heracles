package ar.edu.unlp.bbdd2.heracles.bo;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;

/**
*
* @author Nahuel Diaz <nahd85@gmail.com>
*
*/
public interface ActivityBO {
	
	/**
	 * Indica el porcentaje de progreso de la actividad.
	 * Es representada por la cantidad de ejercicio sobre los que se finalizaron.
	 * 
	 * @param activity
	 * 			La actividad de la que se requiere el progreso.
	 * 
	 * @return porcentaje del progreso
	 */
	public Integer activityProggress (Activity activity);
	
	/**
	 * 
	 * @param activity
	 * 			Actividad de la que se quiere saber el numero de ejercicios
	 * @return
	 * 		Cantidad de ejercicios
	 */
	public Integer exercisesCount (Activity activity);

}
