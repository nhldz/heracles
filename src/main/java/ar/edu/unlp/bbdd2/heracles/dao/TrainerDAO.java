package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.Trainer;

public interface TrainerDAO extends BaseDAO<Trainer> {

	/**
	 * Busca y retorna un profesor por email
	 * 
	 * @param email
	 *            del profesor a buscar
	 * @return
	 */
	public Trainer loadByEmail(String email);
}
