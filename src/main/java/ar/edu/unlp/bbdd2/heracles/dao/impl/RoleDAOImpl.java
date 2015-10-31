package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.Role;

public class RoleDAOImpl extends BaseDAOImpl<Role> {

	public RoleDAOImpl (Class<Role> classType){
		super();
		this.type = classType;
	}
	
	public Role loadByName (String name){
		Role role = ofy().load().type(this.getType()).filter("name", name).first().now();
		return role;
	}
	
}
