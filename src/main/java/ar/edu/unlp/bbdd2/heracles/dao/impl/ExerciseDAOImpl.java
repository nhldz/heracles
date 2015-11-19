package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.dao.ExerciseDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Exercise;

public class ExerciseDAOImpl extends BaseDAOImpl<Exercise> implements ExerciseDAO {

	public ExerciseDAOImpl(Class<Exercise> classType) {
		super();
		this.type = classType;
	}

	public Exercise findByName(String name) {
		Exercise exercise = ofy().load().type(this.getType()).filter("name", name).first().now();
		return exercise;
	}
}
