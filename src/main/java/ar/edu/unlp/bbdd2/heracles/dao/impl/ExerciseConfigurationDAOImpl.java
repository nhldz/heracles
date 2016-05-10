package ar.edu.unlp.bbdd2.heracles.dao.impl;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.dao.ExerciseConfigurationDAO;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseConfiguration;
import ar.edu.unlp.bbdd2.heracles.entities.ExerciseSnapshot;

public class ExerciseConfigurationDAOImpl extends BaseDAOImpl<ExerciseConfiguration> implements ExerciseConfigurationDAO{
	
	public ExerciseConfigurationDAOImpl (Class<ExerciseConfiguration> classType){
		super();
		this.type = classType;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExerciseSnapshot lastSnapshot(ExerciseConfiguration exerciseConfiguration) {
		List<ExerciseConfiguration> exConfs = new ArrayList<ExerciseConfiguration>();
		exConfs.add(exerciseConfiguration);
		return ofy().load().type(ExerciseSnapshot.class).filter("exerciseConfiguration IN", exConfs).order("-endDate").first().now();
	}

}
