/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.dao.ClientDAO;
import ar.edu.unlp.bbdd2.heracles.dao.ExerciseDAO;
import ar.edu.unlp.bbdd2.heracles.dao.TrainerDAO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoleDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoutineDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Role;
import ar.edu.unlp.bbdd2.heracles.entities.RoleName;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;
import ar.edu.unlp.bbdd2.heracles.entities.User;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class TrainerBOImpl implements TrainerBO {

	private ClientDAO clientDAO;
	private TrainerDAO trainerDAO;
	private ExerciseDAO exerciseDAO;
	private RoutineDAOImpl routineDAO;
	private ExerciseConfigurationDAOImpl exConfDAO;
	private ActivityDAOImpl activityDAO;
	private RoleDAOImpl roleDAO;

	/**
	 * {@inheritDoc}
	 */
	public Activity createActivity(Routine routine, String name, String description, Activity next, Activity previous) {
		Activity activity = new Activity();
		activity.setName(name);
		activity.setDescription(description);
		activity.setRoutine(routine);
		activity.setExercises(new ArrayList<ExerciseConfiguration>());
		this.getActivityDAO().save(activity);
		routine.getActivities().add(activity);
		this.getRoutineDAO().saveOrUpdate(routine);
		return activity;
	}

	/**
	 * {@inheritDoc}
	 */
	public ExerciseConfiguration createExConfiguration(Exercise exercise, Activity activity, Integer sets, Integer reps,
			Integer rest, Integer weight) {
		ExerciseConfiguration exConf = new ExerciseConfiguration(exercise, sets, reps, rest, weight);
		this.getExConfDAO().save(exConf);
		activity.getExercises().add(exConf);
		this.getActivityDAO().save(activity);
		return exConf;
	}

	/**
	 * {@inheritDoc}
	 */
	public Routine createRoutine(String name, Trainer trainer, Client client) {
		Routine routine = new Routine(name, trainer, client);
		this.getRoutineDAO().save(routine);
		this.getTrainerDAO().saveOrUpdate(trainer);
		assingRoutine(client, routine);
		return routine;
	}

	private void assingRoutine(Client client, Routine routine) {
		Routine actualRoutine = client.getActualRoutine();
		if (actualRoutine != null) {
			actualRoutine.setEndDate(new Date());
			List<Routine> clientRoutines = client.getRoutines();
			clientRoutines.add(actualRoutine);
			client.setRoutines(clientRoutines);
			this.getRoutineDAO().saveOrUpdate(actualRoutine);
		}
		client.setActualRoutine(routine);
		routine.setClient(client);
		this.getClientDAO().saveOrUpdate(client);
		this.getRoutineDAO().saveOrUpdate(routine);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assingRoutineToClient(Client client, Routine routine) throws BusinessException {
		if (routine.getClient() == null) {
			assingRoutine(client, routine);
		} else {
			throw new BusinessException("La rutina ya esta asignada al cliente: " + routine.getClient().getName());
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
		if (exercise != null) {
			ExerciseSnapshot snapshot = exercise.getSnapshots().get((exercise.getSnapshots().size() - 1));
			snapshot.setEndDate(new Date());
			snapshot.setState(ExerciseState.SKIP);
			client.getActualRoutine().getRunActivity().setRunExercise(null);
		} else {
			throw new BusinessException("El cleinte " + client.getName() + " no esta realizando ningun ejercicio");
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trainer findByEmail(String email) {
		return this.getTrainerDAO().loadByEmail(email);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Trainer createTrainer(Trainer trainer) throws BusinessException {
		if (this.findByEmail(trainer.getEmail()) == null) {
			trainer.setExercises(new ArrayList<Exercise>());
			trainer.setRoutines(new ArrayList<Routine>());
			trainer.setRegistrationDate(new Date());
			trainer.setEnabledUser(true);
			Role role = roleDAO.loadByName(RoleName.TRAINER.getType());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			trainer.setRoles(roles);
			this.getTrainerDAO().save(trainer);
			List<User> users = role.getUsers();
			users.add(trainer);
			role.setUsers(users);
			this.getRoleDAO().saveOrUpdate(role);
		} else {
			throw new BusinessException("El email ya existe");
		}
		return trainer;
	}

	@Override
	public void trainerDisable(Long id) {
		Trainer trainer = this.getTrainerDAO().loadById(id);
		trainer.setEnabledUser(false);
		this.getTrainerDAO().save(trainer);
	}

	@Override
	public Trainer getTrainerById(Long id) {
		return this.getTrainerDAO().loadById(id);
	}

	@Override
	public List<Trainer> getAllTrainers() {
		return this.getTrainerDAO().loadAll();
	}

	@Override
	public void save(Trainer trainer) {
		this.getTrainerDAO().save(trainer);
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public TrainerDAO getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	public ExerciseDAO getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAO exerciseDAO) {
		this.exerciseDAO = exerciseDAO;
	}

	public RoutineDAOImpl getRoutineDAO() {
		return routineDAO;
	}

	public void setRoutineDAO(RoutineDAOImpl routineDAO) {
		this.routineDAO = routineDAO;
	}

	public ExerciseConfigurationDAOImpl getExConfDAO() {
		return exConfDAO;
	}

	public void setExConfDAO(ExerciseConfigurationDAOImpl exConfDAO) {
		this.exConfDAO = exConfDAO;
	}

	public ActivityDAOImpl getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAOImpl activityDAO) {
		this.activityDAO = activityDAO;
	}

	public RoleDAOImpl getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}
}
