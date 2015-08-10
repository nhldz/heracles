package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Date;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Usuario
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Entity
public abstract class User {
	
	@Id
	private Long id;
	private String name;
	private String email;
	private Date registrationDate;
	private Date birthday;
	private Ref<Gender> gender;
	
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
		return gender.get();
	}
	public void setGender(Gender gender) {
		this.gender = Ref.create(gender);
	}

}
