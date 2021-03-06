package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.bo.RoutineBO;
import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.dao.ActivityDAO;
import ar.edu.unlp.bbdd2.heracles.dao.ClientDAO;
import ar.edu.unlp.bbdd2.heracles.dao.ExerciseConfigurationDAO;
import ar.edu.unlp.bbdd2.heracles.dao.ExerciseDAO;
import ar.edu.unlp.bbdd2.heracles.dao.RoutineDAO;
import ar.edu.unlp.bbdd2.heracles.dto.ActivityDTO;
import ar.edu.unlp.bbdd2.heracles.dto.ExerciseConfigurationDTO;
import ar.edu.unlp.bbdd2.heracles.dto.RoutineDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
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

	@Autowired
	private ActivityDAO activityDAO;

	@Autowired
	private ExerciseConfigurationDAO exConfDAO;

	@Autowired
	private ExerciseDAO exerciseDAO;

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private TrainerBO trainerBO;

	@Autowired
	private ClientBO clientBO;

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
				if ((exConf.getSnapshots() != null) && (exConf.getSnapshots().get(exConf.getSnapshots().size() - 1)
						.getState().equals(ExerciseState.STOP))) {
					exCount++;
				}
			}
			if ((exCount != 0) && (exCount == exercises.size())) {
				completeAct.add(activity);
			}
		}
		return completeAct;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Activity getRunActivity(Client client) {
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

	/**
	 * {@inheritDoc}
	 */
	public void setRunActivity(Client client, Activity activity) {
		Routine actualRoutine = client.getActualRoutine();
		actualRoutine.setRunActivity(activity);
		this.getRoutineDAO().saveOrUpdate(actualRoutine);
		this.getClientDAO().saveOrUpdate(client);
	}

	public List<Routine> getClientRoutines(Client client) {
		return this.getRoutineDAO().getClientRoutines(client);
	}

	@Override
	public Routine getRoutineById(Long id) {
		return this.getRoutineDAO().loadById(id);
	}

	@Override
	public void save(RoutineDTO routineDTO) {
		Trainer trainer = getTrainerBO().getTrainerById(routineDTO.getTrainerid());
		Client client = getClientBO().getClientById(routineDTO.getClientid());
		Routine routine = getTrainerBO().createRoutine(routineDTO.getName(), trainer, client);
		routine.setActivities(transformListActivity(routine, routineDTO.getActivities()));
		this.getRoutineDAO().saveOrUpdate(routine);
	}	
	
	@SuppressWarnings(value = { "unused" })
	private List<Activity> transformListActivity(Routine routine, List<ActivityDTO> activitiesDTO) {
		List<Activity> listActivity = new ArrayList<Activity>();
		for (ActivityDTO activityDTO : activitiesDTO) {
			Activity activity = getTrainerBO().createActivity(routine, activityDTO.getName(),
					activityDTO.getDescription(), null, null);
			for (ExerciseConfigurationDTO exConfDTO : activityDTO.getExercises()) {
				Exercise exercise = this.getExerciseDAO().loadById(exConfDTO.getExerciseId());
				ExerciseConfiguration exConf = this.getTrainerBO().createExConfiguration(exercise, activity,
						exConfDTO.getSets(), exConfDTO.getReps(), exConfDTO.getRest(), exConfDTO.getWeigth());
			}
			listActivity.add(activity);
		}
		return listActivity;
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

	public ActivityDAO getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAO activityDAO) {
		this.activityDAO = activityDAO;
	}

	public ExerciseConfigurationDAO getExConfDAO() {
		return exConfDAO;
	}

	public void setExConfDAO(ExerciseConfigurationDAO exConfDAO) {
		this.exConfDAO = exConfDAO;
	}

	public ExerciseDAO getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAO exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public TrainerBO getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBO trainerBO) {
		this.trainerBO = trainerBO;
	}

	public ClientBO getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBO clientBO) {
		this.clientBO = clientBO;
	}

	@Override
	public Integer progress(Routine routine) {
		int activitiesSize = routine.getActivities().size();
		int compleActivitiesSize = this.getCompleteActivities(routine).size();
		int result = 0;
		if (compleActivitiesSize > 0) {
			result = (compleActivitiesSize * 100 / activitiesSize);
		}
		return result;
	}

}
