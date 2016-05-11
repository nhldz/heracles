package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.dao.ActivityDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Activity;

public class ActivityDAOImpl extends BaseDAOImpl<Activity> implements ActivityDAO {

	public ActivityDAOImpl(Class<Activity> classType) {
		super();
		this.type = classType;
	}

}
