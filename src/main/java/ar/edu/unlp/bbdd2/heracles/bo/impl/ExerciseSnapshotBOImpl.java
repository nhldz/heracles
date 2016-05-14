package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.Date;

import ar.edu.unlp.bbdd2.heracles.bo.ExerciceSnapshotBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseSnapshotDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;

/**
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class ExerciseSnapshotBOImpl implements ExerciceSnapshotBO {
	
	private ExerciseSnapshotDAOImpl exerciseSnapshotDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseSnapshot start(ExerciseConfiguration exerciseConfiguration) {
		ExerciseSnapshot snapshot =  new ExerciseSnapshot(exerciseConfiguration, ExerciseState.RUN);
		snapshot.setEndDate(new Date());
		this.getExerciseSnapshotDAO().save(snapshot);
		return snapshot;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseSnapshot stop(ExerciseConfiguration exerciseConfiguration) {
		ExerciseSnapshot snapshot =  new ExerciseSnapshot(exerciseConfiguration, ExerciseState.STOP);
		snapshot.setEndDate(new Date());
		snapshot.setSets(exerciseConfiguration.getSets());
		snapshot.setRest(exerciseConfiguration.getRest());
		snapshot.setReps(exerciseConfiguration.getReps());
		snapshot.setWeigth(exerciseConfiguration.getWeigth());
		
		this.getExerciseSnapshotDAO().save(snapshot);

		return snapshot;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseSnapshot cancel(ExerciseConfiguration exerciseConfiguration, Integer sets, Integer reps, Integer rest,
			Integer weight) {
		ExerciseSnapshot snapshot =  new ExerciseSnapshot(exerciseConfiguration, ExerciseState.CANCEL);
		snapshot.setEndDate(new Date());
		snapshot.setSets(sets);
		snapshot.setRest(rest);
		snapshot.setReps(reps);
		snapshot.setWeigth(weight);
		
		this.getExerciseSnapshotDAO().save(snapshot);
		return snapshot;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseSnapshot skip(ExerciseConfiguration exerciseConfiguration) {

		ExerciseSnapshot snapshot =  new ExerciseSnapshot(exerciseConfiguration, ExerciseState.SKIP);
		snapshot.setEndDate(new Date());
		snapshot.setSets(0);
		snapshot.setRest(0);
		snapshot.setReps(0);
		snapshot.setWeigth(0);
		
		return snapshot;
	}

	public ExerciseSnapshotDAOImpl getExerciseSnapshotDAO() {
		return exerciseSnapshotDAO;
	}

	public void setExerciseSnapshotDAO(ExerciseSnapshotDAOImpl exerciseSnapshotDAO) {
		this.exerciseSnapshotDAO = exerciseSnapshotDAO;
	}

}
