package ar.edu.unlp.bbdd2.heracles.bo.impl;

import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.bo.ClientBO;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ClientDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseConfigurationDAOImpl;
import ar.edu.unlp.bbdd2.heracles.dao.impl.ExerciseSnapshotDAOImpl;
import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseState;

/**
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
public class ClientBOImpl implements ClientBO {
	
	private ClientDAOImpl clientDAO;
	private ExerciseConfigurationDAOImpl exConfDAO;
	private ExerciseSnapshotDAOImpl exSanpshotDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startExercise(Client client, ExerciseConfiguration exercise) throws BusinessException {
		ExerciseConfiguration runExercise = client.getActualRoutine().getRunActivity().getRunExercise();
		if (runExercise == null) {
			ExerciseSnapshot snapshot = new ExerciseSnapshot();
			snapshot.setStartDate(new Date());
			snapshot.setState(ExerciseState.RUN);
			this.getExSanpshotDAO().save(snapshot);
			exercise.getSnapshots().add(snapshot);
			this.getExConfDAO().saveOrUpdate(exercise);
			client.getActualRoutine().getRunActivity().setRunExercise(exercise);
			this.getClientDAO().saveOrUpdate(client);
		} else {
			throw new BusinessException(
					"Actualmente se esta realizando el ejercicio: " + runExercise.getExercise().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopExercise(Client client) throws BusinessException {
		ExerciseConfiguration runExercise = client.getActualRoutine().getRunActivity().getRunExercise();
		if (runExercise != null) {
			ExerciseSnapshot snapshot = runExercise.getSnapshots().get(runExercise.getSnapshots().size() - 1);
			snapshot.setState(ExerciseState.STOP);
			snapshot.setEndDate(new Date());
			this.getExSanpshotDAO().saveOrUpdate(snapshot);
			runExercise = null;
		} else {
			throw new BusinessException("Actualmente no se esta realizando ningun ejercicio en la actividad: "
					+ client.getActualRoutine().getRunActivity().getName());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void calcelExercise(Client client, List<Integer> sets, List<Integer> reps, Integer weight)
			throws BusinessException {
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

	public ClientDAOImpl getClientDAO() {
		return clientDAO;
	}

	public void setClientDAO(ClientDAOImpl clientDAO) {
		this.clientDAO = clientDAO;
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

}
