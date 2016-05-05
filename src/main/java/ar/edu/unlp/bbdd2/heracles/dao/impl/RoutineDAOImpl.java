package ar.edu.unlp.bbdd2.heracles.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.bbdd2.heracles.dao.RoutineDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 * 
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
public class RoutineDAOImpl extends BaseDAOImpl<Routine> implements RoutineDAO {
	
	@Autowired
	private ActivityDAOImpl activityDao;
	
	@Autowired
	private ExerciseConfigurationDAOImpl exConfigDao;
	
	public RoutineDAOImpl(Class<Routine> classType) {
		super();
		this.type = classType;
	}

	@Override
	public List<Routine> getTrainerRoutines(Trainer trainer) {
		List<Routine> routines = ofy().load().type(this.getType()).filter("trainer", trainer).list();
		return routines;
	}

	@Override
	public List<Routine> getClientRoutines(Client client) {
		List<Routine> routines = ofy().load().type(this.getType()).filter("client", client).list();
		return routines;
	}
	
	public void saveRoutine(Routine routine){
		List<Activity> activities = routine.getActivities();
		for (Activity activity : activities) {
			List<ExerciseConfiguration> exConfigurations = activity.getExercises(); 
			for (ExerciseConfiguration exerciseConfiguration : exConfigurations) {
				getExConfigDao().save(exerciseConfiguration);
			}
			getActivityDao().save(activity);
		}
		this.save(routine);
	}

	public ActivityDAOImpl getActivityDao() {
		return activityDao;
	}

	public void setActivityDao(ActivityDAOImpl activityDao) {
		this.activityDao = activityDao;
	}

	public ExerciseConfigurationDAOImpl getExConfigDao() {
		return exConfigDao;
	}

	public void setExConfigDao(ExerciseConfigurationDAOImpl exConfigDao) {
		this.exConfigDao = exConfigDao;
	}
}
