package ar.edu.unlp.bbdd2.heracles.dao.impl;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.dao.RoutineDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Routine;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

/**
 * 
 * @author Matias Garcia <matiasagt@gmail.com>
 *
 */
public class RoutineDAOImpl extends BaseDAOImpl<Routine> implements RoutineDAO {

	public RoutineDAOImpl(Class<Routine> classType) {
		super();
		this.type = classType;
	}

	@Override
	public List<Routine> getTrainerRoutines(Trainer trainer) {
		List<Routine> routines = ofy().load().type(this.getType()).filter("trainer", trainer).list();
		return routines;
	}
}
