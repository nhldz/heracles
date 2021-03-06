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
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoleDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoutineDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.TrainerDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dto.ClientDTO;
import ar.edu.unlp.bbdd2.heracles.dto.TrainerDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.BodyPart;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Equipment;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseType;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Role;
import ar.edu.unlp.bbdd2.heracles.entities.RoleName;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.User;

public class RootUtil {

	private ExerciseDAOImpl exerciseDAO;
	private ExerciseConfigurationDAOImpl exerciseConfigurationDAO;
	private TrainerDAOImpl trainerDAO;
	private ActivityDAOImpl activityDAO;
	private RoutineDAOImpl routineDAO;
	private ClientDAOImpl clientDAO;
	private RoleDAOImpl roleDAO;

	private ExerciseBOImpl exerciseBO;
	private TrainerBOImpl trainerBO;
	private RoutineBOImpl routineBO;
	private ClientBOImpl clientBO;

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

	public RoleDAOImpl getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}

	public void loadRoles() {
		Role roleTrainer = new Role();
		roleTrainer.setName(RoleName.TRAINER.getType());
		roleTrainer.setUsers(new ArrayList<User>());
		roleDAO.save(roleTrainer);
		Role roleClient = new Role();
		roleClient.setName(RoleName.CLIENT.getType());
		roleClient.setUsers(new ArrayList<User>());
		roleDAO.save(roleClient);
	}

	public Client createClient(String name, String surname, String pass) {
		Client client = null;
		ClientDTO clientDTO = null;
		try {
			clientDTO = new ClientDTO();
			clientDTO.setUserName(name);
			clientDTO.setName(name);			
			clientDTO.setSurname(surname);
			clientDTO.setPassword("123");
			clientDTO.setPhone("123-456789");
			clientDTO.setBirthday("01/01/1980");
			clientDTO.setEmail(name + ".client@email.com");
			clientDTO.setGender(Gender.FEMALE);
			client = clientBO.createClient(clientDTO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return client;
	}

	public Trainer createTrainer(String name, String surname, String pass) {
		TrainerDTO trainerDTO = null;
		Trainer trainer = null;		
		try {
			trainerDTO = new TrainerDTO();
			trainerDTO.setUserName(name);
			trainerDTO.setName(name);
			trainerDTO.setSurname(surname);
			trainerDTO.setPassword("123");
			trainerDTO.setPhone("123-456789");
			trainerDTO.setBirthday("01/01/1980");
			trainerDTO.setEmail(name + ".trainer@rmail.com");
			trainerDTO.setGender(Gender.MALE);
			trainer = trainerBO.createTrainer(trainerDTO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
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
		exConf.setReps(12);
		exConf.setSets(3);
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

	public Role createRole(RoleName roleName) {
		Role role = new Role();
		role.setName(roleName.getType());
		role.setUsers(new ArrayList<User>());
		return role;
	}

//	@PostConstruct
	public void rootLoad() {
		int count = trainerDAO.count();
		System.out.println("COUNT: " + count);
		if (count == 0) {
			String clientName = "ClientName";
			String trianerName = "TrainerName";
			String activityName = "ActivityName";
			String surname = "Apellido";
			String exName = "ExName";
			String routineName = "RoutineName";
			String pass = "123";

			List<BodyPart> bodysParts = new ArrayList<BodyPart>();

			bodysParts.add(BodyPart.BICEPS);
			loadRoles();
			Trainer trainer = createTrainer("nahuel", "Diaz", "123");
			trainer = createTrainer("matias", "Garcia", "123");

			ExerciseConfiguration exConf = null;
			Activity activity = null;
			for (int i = 0; i < 5; i++) {
				Client client = createClient(clientName + i, surname + i, pass);
				trainer = createTrainer(trianerName + i, surname + i, pass);
				Exercise exercise = null;
				

				try {
					exercise = this.getExerciseBO().createExercise(trainer, exName + i, ExerciseType.AEROBIC,
							Equipment.MANCUERNA, bodysParts, "Ejercicio #" + i);
				} catch (BusinessException e) {
					exercise = this.getExerciseDAO().findByName(exName + i);
					e.printStackTrace();
				}
				//Se crean 5 rutinas y a esas 5 rutinas se les agrega 5 actividades
				for (int rt = 0; rt < 5; rt++) {
					Routine routine = this.getTrainerBO().createRoutine(routineName + i+"#"+ rt, trainer, client);
					List<Activity> activities = new ArrayList<>();
					for (int act = 0; act < 5; act++) {
						activity = this.getTrainerBO().createActivity(routine, activityName + act,
								"Actividad #" + act, null, null);
						for (int exc = 0; exc < 5; exc++) {
							exConf = this.getTrainerBO().createExConfiguration(exercise, activity, exc, exc, exc, exc);
						}
						activities.add(activity);
					}
					routine.setActivities(activities);
					this.getRoutineDAO().saveOrUpdate(routine);
					
				}
				//Para tener una actividad con ejercicios completos
				try {
					this.getRoutineBO().setRunActivity(client, activity);
					this.getClientBO().startExercise(client, exConf);
					this.getClientBO().stopExercise(client);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			System.out.println("ROOT LOAD");
		}

	}

}
