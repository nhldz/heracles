/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public interface TrainerBO {

	/**
	 * Crea una rutina
	 * 
	 * @param name
	 *            Nombre de la rutina.
	 * @param trainer
	 *            Entrenador que crea la rutina.
	 * @param client
	 *            Cleinte que debe realizar la rutina.
	 * @return
	 */
	public Routine createRoutine(String name, Trainer trainer, Client client);

	/**
	 * Crea una actividad
	 * 
	 * @param routine
	 *            Rutina para la que se crea la actividad.
	 * @param name
	 *            Nombre de la actividad.
	 * @param Description
	 *            Descripción de la actidad.
	 * @param next
	 *            Proxima actividad.
	 * @param previous
	 *            Actividad previa.
	 * @return actividad creada.
	 */
	public Activity createActivity(Routine routine, String name, String description, Activity next, Activity previous);

	/**
	 * Crea una configuracion de un ejercicio para una actividad
	 * 
	 * @param exercise
	 *            Ejercicio para el que se realiza la configuración.
	 * @param activity
	 *            Actividad a la que se le asigna la configuración del ejericio.
	 * @param sets
	 *            Lista de series
	 * @param reps
	 *            Lista de repeticiones
	 * @param rest
	 *            Tiempo de descanzo
	 * @param weight
	 *            Peso con el que se configura el ejercicio.
	 * @return Configuración de ejercicio creada.
	 */
	public ExerciseConfiguration createExConfiguration(Exercise exercise, Activity activity, Integer sets, Integer reps,
			Integer rest, Integer weight);

	/**
	 * Asigna una rutina a un cliente, si la rutina no tiene un cliente. La
	 * rutina es asignada al cliente como rutina actual.
	 * 
	 * @param client
	 * @param routine
	 * @throws BusinessException
	 */
	void assingRoutineToClient(Client client, Routine routine) throws BusinessException;

	/**
	 * Le asigna la copia de una rutina a un cliente.
	 * 
	 * @param client
	 * @param routine
	 */
	void copyRoutine(Client client, Routine routine) throws BusinessException;

	/**
	 * Pone en estado omitidio (skip) al ejercicio que deberia hacerce
	 * actialmente por el usuario.
	 * 
	 * @param client
	 */
	void skipExercise(Client client) throws BusinessException;

	/**
	 * Recupera un entrenador por el email.
	 * 
	 * @param email
	 *            email del entrenador a buscar.
	 * @return
	 */
	public Trainer findByEmail(String email);

	/**
	 * Crea un nuevo entrenador y lo retorna
	 * 
	 * @param trainer
	 * @return Un entrenador (Trainer)
	 * @throws BusinessException
	 */
	public Trainer createTrainer(Trainer trainer) throws BusinessException;

	/**
	 * Deshabilita un profesor
	 * 
	 * @param id
	 *            del profesor a deshabilitar
	 */
	public void trainerDisable(Long id);

	/**
	 * Retorna un profesor
	 * 
	 * @param id
	 *            del profesor
	 */
	public Trainer getTrainerById(Long id);

	/**
	 * Retorna todos los profesores
	 * 
	 */
	public List<Trainer> getAllTrainers();

	public void save(Trainer trainer);
}
