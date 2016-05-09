package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.User;

public class UserDAOImpl extends BaseDAOImpl<User> {
	
	public UserDAOImpl (Class<User> classType){
		super();
		this.type = classType;
	}
	
	public User loadByEmail (String email){
		return ofy().load().type(this.getType()).filter("email", email).first().now();
	}
	
	public User loadByName (String username){
		return ofy().load().type(this.getType()).filter("username", username).first().now();
	}

}
