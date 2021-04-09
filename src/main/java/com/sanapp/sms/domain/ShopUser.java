package com.sanapp.sms.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shop_user database table.
 * 
 */
@Entity
@Table(name="shop_user")
@NamedQuery(name="ShopUser.findAll", query="SELECT s FROM ShopUser s")
public class ShopUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="first_name", nullable = false)
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="password", nullable = false)
	private String password;

	@Column(name="role")
	private String role;

	@Column(name="user_name",nullable = true, unique = true)
	private String userName;

	private Boolean enabled;

	public ShopUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}