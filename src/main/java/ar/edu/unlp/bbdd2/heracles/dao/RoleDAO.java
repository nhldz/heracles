package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.Role;

public interface RoleDAO extends BaseDAO<Role> {

	public Role loadByName(String name);
}
