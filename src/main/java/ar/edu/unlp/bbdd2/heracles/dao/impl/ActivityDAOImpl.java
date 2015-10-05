package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.Activity;

public class ActivityDAOImpl extends BaseDAOImpl<Activity>{
	
	public ActivityDAOImpl (Class<Activity> classType){
		super();
		this.type = classType;
	}
	
}
