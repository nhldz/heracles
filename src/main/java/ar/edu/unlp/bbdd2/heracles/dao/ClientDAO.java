package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.Client;

public interface ClientDAO extends BaseDAO<Client> {

	/**
	 * Busca y retorna un cliente por email
	 * 
	 * @param email
	 *            del cliente a buscar
	 * @return
	 */
	public Client loadByEmail(String email);

}
