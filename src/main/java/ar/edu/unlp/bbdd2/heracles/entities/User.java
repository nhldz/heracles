package ar.edu.unlp.bbdd2.heracles.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Ref;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;

import ar.edu.unlp.bbdd2.heracles.helper.RefHelper;

/**
 * Usuario
 *
 * @author Nahuel Diaz <nahd85@gmail.com>
 *
 */
@Entity
public abstract class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7962922856295831694L;

	@Id
	private Long id;
	@Index
	private String name;
	private String surname;
	@Index
	private String email;
	private String password;
	private String phone;
	private boolean enabledUser;
	@Load
	private List<Ref<Role>> roles;
	private Date registrationDate;
	private Date birthday;
	private Gender gender;

	public User() {
		super();
	}

	public User(String name, String surname, String email, Date birthday, Gender gender) {
		this();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.birthday = birthday;
		this.gender = gender;
		this.enabledUser = true;
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
		return RefHelper.deref(roles);
	}

	public void setRoles(List<Role> roles) {
		this.roles = RefHelper.ref(roles);
	}

}
