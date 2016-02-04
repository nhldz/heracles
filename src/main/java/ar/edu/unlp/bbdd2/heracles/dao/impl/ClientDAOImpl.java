package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.dao.ClientDAO;
import ar.edu.unlp.bbdd2.heracles.entities.Client;

public class ClientDAOImpl extends BaseDAOImpl<Client> implements ClientDAO{
	
	public ClientDAOImpl (Class<Client> classType){
		super();
		this.type = classType;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client loadByEmail (String email){
		return ofy().load().type(this.getType()).filter("email", email).first().now();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Client loadByName(String name){
		return ofy().load().type(this.getType()).filter("name", name).first().now();
	}
	
}
