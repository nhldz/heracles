package ar.edu.unlp.bbdd2.heracles.dao.impl;

import ar.edu.unlp.bbdd2.heracles.entities.Client;

public class ClientDAOImpl extends BaseDAOImpl<Client> {
	
	public ClientDAOImpl (Class<Client> classType){
		super();
		this.type = classType;
	}
	
}