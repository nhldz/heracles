package ar.edu.unlp.bbdd2.heracles.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.impl.BusinessException;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ClientBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.ExerciseBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.RoutineBOImpl;
import ar.edu.unlp.bbdd2.heracles.bo.impl.TrainerBOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ClientDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoutineDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.TrainerDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.UserDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public class RootUtil {

	private ExerciseDAOImpl exerciseDAO;
	private ExerciseConfigurationDAOImpl exerciseConfigurationDAO;
	private TrainerDAOImpl trainerDAO;
	private ActivityDAOImpl activityDAO;
	private RoutineDAOImpl routineDAO;
	private ClientDAOImpl clientDAO;
	private UserDAOImpl userDAO;

	private ExerciseBOImpl exerciseBO;
	private TrainerBOImpl trainerBO;
	private RoutineBOImpl routineBO;
	private ClientBOImpl clientBO;
	private boolean loaded = false;

	public ExerciseDAOImpl getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAOImpl exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public ExerciseConfigurationDAOImpl getExerciseConfigurationDAO() {
		return exerciseConfigurationDAO;
	}

	public void setExerciseConfigurationDAO(ExerciseConfigurationDAOImpl exerciseConfigurationDAO) {
		this.exerciseConfigurationDAO = exerciseConfigurationDAO;
	}

	public TrainerDAOImpl getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAOImpl trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	public ActivityDAOImpl getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAOImpl activityDAO) {
		this.activityDAO = activityDAO;
	}

	public RoutineDAOImpl getRoutineDAO() {
		return routineDAO;
	}

	public void setRoutineDAO(RoutineDAOImpl routineDAO) {
		this.routineDAO = routineDAO;
	}

	public ClientDAOImpl getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAOImpl clientDAO) {
		this.clientDAO = clientDAO;
	}

	public ExerciseBOImpl getExerciseBO() {
		return exerciseBO;
	}

	public void setExerciseBO(ExerciseBOImpl exerciseBO) {
		this.exerciseBO = exerciseBO;
	}

	public TrainerBOImpl getTrainerBO() {
		return trainerBO;
	}

	public void setTrainerBO(TrainerBOImpl trainerBO) {
		this.trainerBO = trainerBO;
	}

	public RoutineBOImpl getRoutineBO() {
		return routineBO;
	}

	public void setRoutineBO(RoutineBOImpl routineBO) {
		this.routineBO = routineBO;
	}

	public ClientBOImpl getClientBO() {
		return clientBO;
	}

	public void setClientBO(ClientBOImpl clientBO) {
		this.clientBO = clientBO;
	}

	public Client createClient(String name, String pass) {
		Client client = new Client();
		client.setName(name);
		client.setSurname("apellido");
		client.setBirthday(new Date());
		client.setEmail(name + ".client@email.com");
		client.setCellPhone("1322154");
		client.setGender(Gender.MALE);
		client.setRoutines(new ArrayList<Routine>());
		client.setPassword(pass);
		this.getClientDAO().save(client);
		return client;
	}

	public Trainer createTrainer(String name, String pass) {
		Trainer trainer = new Trainer();
		trainer.setName(name);
		trainer.setSurname("apellido");
		trainer.setBirthday(new Date());
		trainer.setEmail(name + ".trainer@email.com");
		trainer.setCellPhone("1322154");
		trainer.setGender(Gender.MALE);
		trainer.setExercises(new ArrayList<Exercise>());
		trainer.setPassword(pass);
		trainer.setRoutines(new ArrayList<Routine>());
		this.getTrainerDAO().save(trainer);
		return trainer;
	}

	public Exercise createExercise(String name) {
		Exercise exercise = new Exercise();
		exercise.setBodyParts(new ArrayList<BodyPart>());
		exercise.setName(name);
		return exercise;
	}

	public ExerciseConfiguration createExConf() {
		ExerciseConfiguration exConf = new ExerciseConfiguration();
		exConf.setReps(new ArrayList<Integer>());
		exConf.getReps().add(1);
		exConf.getReps().add(2);
		exConf.getReps().add(3);
		exConf.setSets(new ArrayList<Integer>());
		exConf.getSets().add(1);
		exConf.getSets().add(2);
		exConf.getSets().add(3);
		exConf.setWeigth(10);
		return exConf;
	}

	public Activity createActivity(String name) {
		Activity activity = new Activity();
		activity.setDescription("Activity description " + name);
		activity.setExercises(new ArrayList<ExerciseConfiguration>());
		activity.setName(name);
		return activity;
	}

	public Routine createRoutine(String name) {
		Routine routine = new Routine();
		routine.setActivities(new ArrayList<Activity>());
		routine.setCreateDate(new Date());
		routine.setName(name);
		return routine;
	}

	public void rootLoad() {
		if (!loaded) {
			String clientName = "ClientName";
			String trianerName = "TrainerName";
			String activityName = "ActivityName";
			String exName = "ExName";
			String routineName = "RoutineName";
			String pass = "123456";
			List<BodyPart> bodysParts = new ArrayList<BodyPart>();
			bodysParts.add(BodyPart.BICEPS);
			Trainer trainer = createTrainer("nahuel", "123");
			for (int i = 6; i < 10; i++) {
				Client client = createClient(clientName + i, pass);
				client.setPassword(pass);
				trainer = createTrainer(trianerName + i, pass);
				Exercise exercise = null;
				// Activity activity = createActivity(activityName+i);
				// Routine routine = createRoutine(routineName+i);
				// ExerciseConfiguration exConf = createExConf();
				// exercise.setOwner(trainer);
				// trainer.

				try {
					exercise = this.getExerciseBO().createExercise(trainer, exName + i, ExerciseType.AEROBIC,
							Equipment.MANCUERNA, bodysParts, "Ejercicio #" + i);
				} catch (BusinessException e) {
					exercise = this.getExerciseDAO().findByName(exName + i);
					e.printStackTrace();
				}
				Routine routine = this.getTrainerBO().createRoutine(routineName + i, trainer, client);
				Activity activity = this.getTrainerBO().createActivity(routine, activityName + i, "Actividad #" + i,
						null, null);
				List<Integer> sets = new ArrayList<Integer>();
				List<Integer> reps = new ArrayList<Integer>();
				sets.add(i);
				reps.add(i);
				this.getTrainerBO().createExConfiguration(exercise, activity, sets, reps, i, i);

			}
			List<Client> lista = this.getClientDAO().loadAll();
			List<Trainer> trainer2 = this.getTrainerDAO().loadAll();
			System.out.println("ROOT LOAD");
			loaded = true;
		}

	}

	public UserDAOImpl getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

}
