package ar.edu.unlp.bbdd2.heracles.dao;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Client;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 * 
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
public interface RoutineDAO extends BaseDAO<Routine>{

	List<Routine> getTrainerRoutines(Trainer trainer);
	
	/**
	 * Rutinas de un cliente
	 * @param client
	 * @return
	 */
	List<Routine> getClientRoutines(Client client);

}
