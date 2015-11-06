package ar.edu.unlp.bbdd2.heracles.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


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
	@Index
	private String name;
	private String surname;
	private String cellPhone;
	@Index
	private String email;
	private String password;
	private boolean enabledUser;
	//private List<Ref<Role>> roles;
	private Date registrationDate;
	private Date birthday;
	private Gender gender;

	public User() {
		this.setEnabledUser(true);
		this.setRegistrationDate(Calendar.getInstance().getTime());
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

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
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

	public boolean isEnabledUser() {
		return enabledUser;
	}

	public void setEnabledUser(boolean enabledUser) {
		this.enabledUser = enabledUser;
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

//	public List<Role> getRoles() {
//		return RefHelper.deref(roles);
//	}
//	public void setRoles(List<Role> roles) {
//		this.roles = RefHelper.ref(roles);
//	}

	
}
