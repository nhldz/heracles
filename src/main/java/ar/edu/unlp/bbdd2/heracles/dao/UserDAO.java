package ar.edu.unlp.bbdd2.heracles.dao;

import ar.edu.unlp.bbdd2.heracles.entities.User;

public interface UserDAO extends BaseDAO<User> {

	User loadByUserName(String userName);

}
