package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.dao.TrainerDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public class TrainerDAOImpl extends BaseDAOImpl<Trainer> implements TrainerDAO{
	
	public TrainerDAOImpl (Class<Trainer> classType){
		super();
		this.type = classType;
	}
	
	public Trainer loadByEmail (String email){
		return ofy().load().type(this.getType()).filter("email", email).first().now();
	}

}
