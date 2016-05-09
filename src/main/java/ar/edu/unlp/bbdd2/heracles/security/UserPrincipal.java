package ar.edu.unlp.bbdd2.heracles.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Role;
import ar.edu.unlp.bbdd2.heracles.entities.User;

public class UserPrincipal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7578549878617243943L;

	private Long id;
	private String name;
	private String email;
	private List<String> roles;
	private Date registrationDate;
	private Date birthday;
	private Gender gender;
	private Class<?> userType;

	public UserPrincipal() {
	}

	public UserPrincipal(Long id, String name, String email, List<Role> roles, Date registrationDate, Date birthday,
			Gender gender) {
		this();
		this.id = id;
		this.name = name;
		this.email = email;
		this.roles = new ArrayList<String>();
		for (Role role : roles) {
			this.roles.add(role.getName());
		}
		
		this.registrationDate = registrationDate;
		this.birthday = birthday;
		this.gender = gender;
	}
	
	public UserPrincipal (User user){
		this(user.getId(), user.getUsername(), user.getEmail(), user.getRoles(),
				user.getRegistrationDate(), user.getBirthday(),user.getGender());
		this.userType = user.getClass();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Class<?> getUserType() {
		return userType;
	}

	public void setUserType(Class<?> userType) {
		this.userType = userType;
	}
	
}
