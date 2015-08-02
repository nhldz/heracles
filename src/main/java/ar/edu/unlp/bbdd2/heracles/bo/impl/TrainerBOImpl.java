/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.Date;

import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class TrainerBOImpl implements TrainerBO{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assingRoutine(Client client, Routine routine) throws BusinessException {
		if (routine.getClient() == null){
			Routine actualRoutine = client.getActualRoutine();
			if (actualRoutine != null){
				actualRoutine.setEndDate(new Date());
				client.getRoutines().add(actualRoutine);
			}
			client.setActualRoutine(routine);
			routine.setClient(client);
		}else {
			throw new BusinessException("La rutina ya esta asignada al cliente: "+routine.getClient().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copyRoutine(Client client, Routine routine) throws BusinessException {
		Routine copyOfRoutine = new Routine(routine, client);
		
		for (Activity activity : routine.getActivities()) {
			Activity copyActivity = new Activity();
			copyActivity.setName(activity.getName());
			copyActivity.setDescription(activity.getDescription());
			copyOfRoutine.getActivities().add(copyActivity);
		}
		this.assingRoutine(client, copyOfRoutine);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void skipExercise(Client client) throws BusinessException {
		ExerciseConfiguration exercise = client.getActualRoutine().getRunActivity().getRunExercise();
		if (exercise !=null){
			ExerciseSnapshot snapshot = exercise.getSnapshots().get((exercise.getSnapshots().size()-1));
			snapshot.setEndDate(new Date());
			snapshot.setState(ExerciseState.SKIP);
			client.getActualRoutine().getRunActivity().setRunExercise(null);
		}else {
			throw new BusinessException("El cleinte "+ client.getName() +" no esta realizando ningun ejeri");
		}
		
	}

}
