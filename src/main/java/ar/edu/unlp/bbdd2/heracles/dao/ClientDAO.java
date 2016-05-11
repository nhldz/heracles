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
	
	/**
	 * Busca y retorna un cliente por usuario
	 * 
	 * @param username
	 *            del cliente a buscar
	 * @return
	 */
	public Client loadByUserName(String username);

	/**
	 * Busca y retorna un cliente por usuario
	 * 
	 * @param name
	 *            del cliente a buscar
	 * @return
	 */
	public Client loadByName(String name);

}
