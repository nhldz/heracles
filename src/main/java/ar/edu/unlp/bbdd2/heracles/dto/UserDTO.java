package ar.edu.unlp.bbdd2.heracles.dto;

import java.util.List;

import ar.edu.unlp.bbdd2.heracles.entities.Gender;
import ar.edu.unlp.bbdd2.heracles.entities.Role;
import ar.edu.unlp.bbdd2.heracles.entities.User;
import ar.edu.unlp.bbdd2.heracles.util.Utilities;

/**
 *  DTO del usuario.
 * 
 * 
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public class UserDTO {
	
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phone;
	private boolean enabledUser;
	private List<Role> roles;
	private String registrationDate;
	private String birthday;
	private Gender gender;
	
	
	public UserDTO (){
		
	}
	
	public UserDTO (User user) {
		this();
		this.id = user.getId();
		this.name = user.getUsername();
		this.surname = user.getSurname();
		this.email = user.getEmail();
		this.password = null;
		this.phone = user.getPhone();
		this.enabledUser = user.isEnabledUser();
//		this.roles = user.getRoles();
		this.registrationDate = Utilities.formatDateToString(user.getRegistrationDate());
		this.birthday = Utilities.formatDateToString(user.getBirthday());
		this.gender = user.getGender();
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isEnabledUser() {
		return enabledUser;
	}
	public void setEnabledUser(boolean enabledUser) {
		this.enabledUser = enabledUser;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	

}
