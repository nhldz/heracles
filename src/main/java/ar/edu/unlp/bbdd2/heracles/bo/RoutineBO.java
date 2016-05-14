package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface RoutineBO {

	/**
	 * Retorna todas las actividades que a la fecha tienen los ejercicios
	 * completos.
	 * 
	 * @return Lista de actividades
	 */
	List<Activity> getCompleteActivities(Routine routine);

	/**
	 * Retorna la actividad actual. La actividad actual es la que se considera
	 * debería estar realizando el cliente.
	 * 
	 * @return Actividad
	 */
	Activity getRunActivity(Client client);

	/**
	 * Retorna aquellas actividades que contienen ejercicios cancelados.
	 * 
	 * @return Lista de actividades canceladas
	 */
	List<Activity> getCanceledActivities(Routine routine);

	/**
	 * Retorna una rutina
	 * 
	 * @param id
	 *            de la rutina
	 * @return
	 */
	Routine getRoutineById(Long id);

	void save(RoutineDTO routine);

	/**
	 * Retorna las rutinas creadas por un profesor
	 * 
	 * @param trainer
	 * 
	 * @return
	 */
	List<Routine> getTrainerRoutines(Trainer trainer);

	/**
	 * Retorna todas las rutinas
	 * 
	 * @return
	 */
	List<Routine> getAllRoutines();
	
	/**
	 * Retorna las rutinas que tiene un cliente.
	 * 
	 * @param client
	 * 
	 * @return
	 */
	List<Routine> getClientRoutines(Client client);
	
	/**
	 * Setea en la rutina actual del cliente la actividad
	 */
	void setRunActivity (Client client, Activity activity);
	
	/**
	 * Retorn un entero que representa el porcentaje de avance en en actividades.
	 * 
	 * @param routine
	 * @return integer
	 */
	Integer progress (Routine routine);

}
