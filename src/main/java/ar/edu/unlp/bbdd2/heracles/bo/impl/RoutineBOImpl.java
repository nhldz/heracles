package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.dao.RoutineDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 * 
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
public class RoutineBOImpl implements RoutineBO {

	@Autowired
	private RoutineDAO routineDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Activity> getCompleteActivities(Routine routine) {
		List<Activity> completeAct = new ArrayList<Activity>();
		List<Activity> activities = routine.getActivities();
		for (Activity activity : activities) {
			List<ExerciseConfiguration> exercises = activity.getExercises();
			int exCount = 0;
			for (ExerciseConfiguration exConf : exercises) {
				if (exConf.getSnapshots().get(exConf.getSnapshots().size() - 1).getState().equals(ExerciseState.STOP)) {
					exCount++;
				}
			}
			if (exCount == exercises.size()) {
				completeAct.add(activity);
			}
		}
		return completeAct;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Activity getActualActivity(Client client) {
		return client.getActualRoutine().getRunActivity();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Activity> getCanceledActivities(Routine routine) {
		List<Activity> activities = new ArrayList<Activity>();
		for (Activity activity : routine.getActivities()) {
			for (ExerciseConfiguration exConf : activity.getExercises()) {
				Iterator<ExerciseSnapshot> iterator = exConf.getSnapshots().iterator();
				boolean go = true;
				while (iterator.hasNext() && go) {
					if (iterator.next().getState().equals(ExerciseState.CANCEL)) {
						activities.add(activity);
						go = false;
					}
				}
			}
		}
		return activities;
	}

	@Override
	public Routine getRoutineById(Long id) {
		return this.getRoutineDAO().loadById(id);
	}

	@Override
	public void save(Routine routine) {
		this.getRoutineDAO().saveOrUpdate(routine);
	}

	@Override
	public List<Routine> getTrainerRoutines(Trainer trainer) {
		return this.getRoutineDAO().getTrainerRoutines(trainer);
	}

	@Override
	public List<Routine> getAllRoutines() {
		return this.getRoutineDAO().loadAll();
	}

	public RoutineDAO getRoutineDAO() {
		return routineDAO;
	}

	public void setRoutineDAO(RoutineDAO routineDAO) {
		this.routineDAO = routineDAO;
	}

}
