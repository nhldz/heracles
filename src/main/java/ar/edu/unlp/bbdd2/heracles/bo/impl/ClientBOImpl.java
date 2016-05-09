package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.dao.ClientDAO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ActivityDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseSnapshotDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.RoleDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dto.ClientDTO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;
import ar.edu.unlp.bbdd2.heracles.entities.Role;
import ar.edu.unlp.bbdd2.heracles.entities.RoleName;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.User;
import ar.edu.unlp.bbdd2.heracles.util.Utilities;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class ClientBOImpl implements ClientBO {

	private ClientDAO clientDAO;
	private RoleDAOImpl roleDAO;
	private ExerciseConfigurationDAOImpl exConfDAO;
	private ExerciseSnapshotDAOImpl exSanpshotDAO;
	private ActivityDAOImpl activityDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startExercise(Client client, ExerciseConfiguration exercise) throws BusinessException {
		Activity activity = client.getActualRoutine().getRunActivity();
		try {
			ExerciseConfiguration runExercise = activity.getRunExercise();

			if (runExercise.getSnapshots().get((runExercise.getSnapshots().size() - 1))
					.getState() != ExerciseState.RUN) {
				activity.setRunExercise(runExercise);
				this.getActivityDAO().saveOrUpdate(activity);
			} else {
				throw new BusinessException(
						"Actualmente se esta realizando el ejercicio: " + runExercise.getExercise().getName());
			}

		} catch (NullPointerException e) {
			ExerciseSnapshot snapshot = new ExerciseSnapshot();
			snapshot.setStartDate(new Date());
			snapshot.setState(ExerciseState.RUN);
			this.getExSanpshotDAO().save(snapshot);
			List<ExerciseSnapshot> snapshots = exercise.getSnapshots();
			if (snapshots == null) {
				snapshots = new ArrayList<ExerciseSnapshot>();
			}
			snapshots.add(snapshot);
			exercise.setSnapshots(snapshots);
			this.getExSanpshotDAO().save(snapshot);
			this.getExConfDAO().saveOrUpdate(exercise);
			client.getActualRoutine().getRunActivity().setRunExercise(exercise);
			this.getClientDAO().saveOrUpdate(client);
			activity.setRunExercise(exercise);
			this.getActivityDAO().saveOrUpdate(activity);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopExercise(Client client) throws BusinessException {
		Activity activity = client.getActualRoutine().getRunActivity();
		ExerciseConfiguration runExercise = activity.getRunExercise();
		if (runExercise != null) {
			ExerciseSnapshot snapshot = runExercise.getSnapshots().get(runExercise.getSnapshots().size() - 1);
			snapshot.setState(ExerciseState.STOP);
			snapshot.setEndDate(new Date());
			this.getExSanpshotDAO().saveOrUpdate(snapshot);
		} else {
			throw new BusinessException("Actualmente no se esta realizando ningun ejercicio en la actividad: "
					+ client.getActualRoutine().getRunActivity().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calcelExercise(Client client, Integer sets, Integer reps, Integer weight) throws BusinessException {
		ExerciseConfiguration runExercise = client.getActualRoutine().getRunActivity().getRunExercise();
		if (runExercise != null) {
			ExerciseSnapshot snapshot = runExercise.getSnapshots().get(runExercise.getSnapshots().size() - 1);
			snapshot.setState(ExerciseState.CANCEL);
			snapshot.setEndDate(new Date());
			snapshot.setSets(sets);
			snapshot.setReps(reps);
			snapshot.setWeigth(weight);
			this.getExSanpshotDAO().saveOrUpdate(snapshot);
			runExercise.getSnapshots().add(snapshot);
			runExercise = null;
			this.getExConfDAO().saveOrUpdate(runExercise);
		} else {
			throw new BusinessException("Actualmente no se esta realizando ningun ejercicio en la actividad: "
					+ client.getActualRoutine().getRunActivity().getName());
		}
	}

	public List<Client> getAllClients() {
		return this.getClientDAO().loadAll();
	}

	@Override
	public Client getClientById(Long id) {
		return this.getClientDAO().loadById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client createClient(ClientDTO clientDTO) throws BusinessException {
		Client client = null;
		if (this.getClientDAO().loadByEmail(clientDTO.getEmail()) == null) {
			Date fecha = Utilities.formatDate(clientDTO.getBirthday());
			client = new Client(clientDTO.getName(), clientDTO.getSurname(), clientDTO.getEmail(), fecha,
					clientDTO.getGender());
			client.setPassword(clientDTO.getPassword());
			client.setPhone(clientDTO.getPhone());
			client.setRoutines(new ArrayList<Routine>());
			client.setRegistrationDate(new Date());
			Role role = roleDAO.loadByName(RoleName.CLIENT.getType());
			List<Role> roles = new ArrayList<Role>();
			roles.add(role);
			client.setRoles(roles);
			clientDAO.save(client);
			List<User> users = role.getUsers();
			users.add(client);
			role.setUsers(users);
			roleDAO.saveOrUpdate(role);
			return client;
		} else {
			throw new BusinessException("El email ya existe");
		}
	}

	@Override
	public Client updateClient(ClientDTO clientDTO) throws BusinessException {
		if (this.getClientDAO().loadByEmail(clientDTO.getEmail()) == null) {
			Client client = clientDAO.loadById(clientDTO.getId());
			client.setUsername(clientDTO.getName());
			client.setSurname(clientDTO.getSurname());
			client.setPhone(clientDTO.getPhone());
			client.setBirthday(Utilities.formatDate(clientDTO.getBirthday()));
			client.setEmail(clientDTO.getEmail());
			client.setGender(clientDTO.getGender());
			getClientDAO().saveOrUpdate(client);
			return client;
		} else {
			throw new BusinessException("El email ya existe");
		}
	}

	@Override
	public void clientDisable(Long idL) {
		Client client = this.getClientDAO().loadById(idL);
		client.setEnabledUser(false);
		this.getClientDAO().save(client);
	}

	@Override
	public List<Client> getAllEnabledClients() {
		return this.getClientDAO().loadAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client getClientByName(String name) {
		return this.getClientDAO().loadByName(name);
	}

	public ClientDAO getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public RoleDAOImpl getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAOImpl roleDAO) {
		this.roleDAO = roleDAO;
	}

	public ExerciseConfigurationDAOImpl getExConfDAO() {
		return exConfDAO;
	}

	public void setExConfDAO(ExerciseConfigurationDAOImpl exConfDAO) {
		this.exConfDAO = exConfDAO;
	}

	public ExerciseSnapshotDAOImpl getExSanpshotDAO() {
		return exSanpshotDAO;
	}

	public void setExSanpshotDAO(ExerciseSnapshotDAOImpl exSanpshotDAO) {
		this.exSanpshotDAO = exSanpshotDAO;
	}

	public ActivityDAOImpl getActivityDAO() {
		return activityDAO;
	}

	public void setActivityDAO(ActivityDAOImpl activityDAO) {
		this.activityDAO = activityDAO;
	}

}
