package com.demo.loginportal.model.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.loginportal.model.auth.User;

@Entity
@Table(name = "FI_SESSION_DATA")
public class SessionUserModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", updatable = true, nullable = false)
	private Long id;

	@Column(name = "sid", nullable = false)
	private String sid;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "role")
	private String role;

	@Column(name = "email", nullable = false, length = 200)
	private String email;

	public SessionUserModel(String sid, User currUser) {
		this.sid = sid;
		this.userName = currUser.getUserName();
		this.firstName = currUser.getFirstName();
		this.lastName = currUser.getLastName();
		this.role = currUser.getRole();
		this.email = currUser.getMailId();
	}

	public SessionUserModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SessionUserModel [id=" + id + ", sid=" + sid + ", userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", role=" + role + ", email=" + email + "]";
	}

}
