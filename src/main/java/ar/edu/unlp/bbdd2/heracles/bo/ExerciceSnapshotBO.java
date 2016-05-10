package ar.edu.unlp.bbdd2.heracles.bo;

import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;

/**
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public interface ExerciceSnapshotBO {
	
	/**
	 * Crea una instacia de snapshot con el estado RUN
	 * 
	 * @param exerciseConfiguration
	 * 		El exerciseConfiguration al la que pertenece el snapshot a crear.
	 * 
	 * @return el snapshot creado.
	 * 		
	 */
	public ExerciseSnapshot start(ExerciseConfiguration exerciseConfiguration);
	
	/**
	 * Crea una instacia de snapshot con el estado STOP 
	 * 
	 * @param exerciseConfiguration
	 * 		El exerciseConfiguration al la que pertenece el snapshot a crear.
	 * 
	 * @return el snapshot creado.
	 */
	public ExerciseSnapshot stop(ExerciseConfiguration exerciseConfiguration);
	

	/**
	 * Crea una instacia de snapshot con el estado CANCEL 
	 * 
	 * @param exerciseConfiguration
	 * 		El exerciseConfiguration al la que pertenece el snapshot a crear.
	 * 
	 * @return el snapshot creado.
	 */
	
	/**
	 * Crea una instacia de snapshot con el estado CANCEL  
	 * 
	 * @param exerciseSnapshot 
	 * 			El exerciseConfiguration al la que pertenece el snapshot a crear.
	 * @param sets
	 * 			Cantidad de series con las que se cancelo el ejercicio.
	 * @param reps
	 * 			Cantidad de repeticiones con las que se cancelo el ejercicio.
	 * @param rest
	 * 			Tipo de descanso utilizado.
	 * @param weight
	 * 			Peso con el que se cancelo el ejericio.
	 * @return
	 */
	public ExerciseSnapshot cancel(ExerciseConfiguration exerciseConfiguration, Integer sets, Integer reps, Integer rest, Integer weight);
	
	/**
	 * Crea una instacia de snapshot con el estado SKIP 
	 * 
	 * @param exerciseConfiguration
	 * 		El exerciseConfiguration al la que pertenece el snapshot a crear.
	 * 
	 * @return el snapshot creado.
	 */
	public ExerciseSnapshot skip(ExerciseConfiguration exerciseConfiguration);

}
