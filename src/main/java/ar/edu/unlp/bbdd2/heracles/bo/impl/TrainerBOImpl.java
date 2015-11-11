/**
 * 
 */
package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.TrainerBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ClientDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoleDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoutineDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.TrainerDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Gender;
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

	private ClientDAOImpl clientDAO;
	private TrainerDAOImpl trainerDAO;
	private ExerciseDAOImpl exerciseDAO;
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
	public ExerciseConfiguration createExConfiguration(Exercise exercise, Activity activity, List<Integer> sets,
			List<Integer> reps, Integer rest, Integer weight) {
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
		this.getClientDAO().saveOrUpdate(client);
		return routine;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void assingRoutine(Client client, Routine routine) throws BusinessException {
		if (routine.getClient() == null) {
			Routine actualRoutine = client.getActualRoutine();
			if (actualRoutine != null) {
				actualRoutine.setEndDate(new Date());
				client.getRoutines().add(actualRoutine);
			}
			client.setActualRoutine(routine);
			routine.setClient(client);
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
	public Trainer createTrainer(String name, String email, Date birthday, Gender gender) throws BusinessException {
		Trainer trainer = null;
		if (this.findByEmail(email) == null) {
			trainer = new Trainer(name, email, birthday, gender);
			trainer.setExercises(new ArrayList<Exercise>());
			trainer.setRoutines(new ArrayList<Routine>());
			trainer.setRegistrationDate(new Date());
			Role role = roleDAO.loadByName(RoleName.TRAINER.getType());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			trainer.setRoles(roles);
			trainerDAO.save(trainer);
			List<User> users = role.getUsers();
			users.add(trainer);
			role.setUsers(users);
			roleDAO.saveOrUpdate(role);
		} else {
			throw new BusinessException("El email ya existe");
		}
		return trainer;
	}

	public ClientDAOImpl getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAOImpl clientDAO) {
		this.clientDAO = clientDAO;
	}

	public TrainerDAOImpl getTrainerDAO() {
		return trainerDAO;
	}

	public void setTrainerDAO(TrainerDAOImpl trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	public ExerciseDAOImpl getExerciseDAO() {
		return exerciseDAO;
	}

	public void setExerciseDAO(ExerciseDAOImpl exerciseDAO) {
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

	public List<Trainer> getAllTrainers() {
		return this.getTrainerDAO().loadAll();
	}

	public RoleDAOImpl getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}

}
