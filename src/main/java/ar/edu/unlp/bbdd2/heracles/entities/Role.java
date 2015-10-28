package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Representa los Roles dentro de la aplicación.
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

@Entity
public class Role {
	
	@Id
	private Long id;
	@Index
	private String name;
	private List<Ref<User>> users;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<User> getUsers() {
		return RefHelper.deref(users);
	}
	public void setUsers(List<User> users) {
		this.users = RefHelper.ref(users);
	}
	
}
