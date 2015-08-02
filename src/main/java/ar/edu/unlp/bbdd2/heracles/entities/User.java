package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Date;

/**
 * Usuario
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */

public abstract class User {
	
	private String name;
	private String email;
	private Date registrationDate;
	private Date birthday;
	private Gender gender;
	
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

}
