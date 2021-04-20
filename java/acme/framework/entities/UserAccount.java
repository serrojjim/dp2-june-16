/*
 * UserAccount.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.framework.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import acme.datatypes.UserIdentity;
import acme.entities.task.Task;
import acme.framework.helpers.PasswordHelper;
import acme.framework.helpers.StringHelper;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserAccount extends DomainEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotBlank
	@Length(min = 5, max = 60)
	@Column(unique = true)
	protected String			username;

	@NotBlank
	@Length(min = 5, max = 60)
	protected String			password;


	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		assert password == null || password.equals("") || !PasswordHelper.isEncoded(password);

		if (!StringHelper.isBlank(password)) {
			this.password = PasswordHelper.encode(password);
		}
	}


	protected boolean		enabled;

	@Valid
	protected UserIdentity	identity;

	// Derived attributes -----------------------------------------------------


	@Transient
	public boolean isAnonymous() {
		boolean result;

		result = this.username.equals("anonymous");

		return result;
	}

	@Transient
	public boolean isManager() {
		boolean result;

		result = this.username.equals("manager");

		return result;
	}
	// Relationships ----------------------------------------------------------


	@NotEmpty
	@OneToMany(mappedBy = "userAccount")
	protected Collection<@Valid UserRole> roles;

	@OneToMany(mappedBy = "userAccount")
	protected List<Task> task;
	
	@Transient
	public boolean hasRole(final UserRole role) {
		assert role != null;

		boolean result;

		result = this.roles != null && this.roles.contains(role);

		return result;
	}

	@Transient
	public boolean hasRole(final Class<? extends UserRole> clazz) {
		boolean result;

		result = this.roles != null && this.getRole(clazz) != null;

		return result;
	}

	@Transient
	@SuppressWarnings("unchecked")
	public <T extends UserRole> T getRole(final Class<? extends UserRole> clazz) {
		assert clazz != null;

		T result;
		Iterator<UserRole> iterator;
		UserRole role;

		result = null;
		if (this.roles != null) {
			iterator = this.roles.iterator();
			while (result == null && iterator.hasNext()) {
				role = iterator.next();
				result = role.getClass().equals(clazz) ? (T) role : null;
			}
		}

		return result;
	}

	@Transient
	@SuppressWarnings("unchecked")
	public <T extends UserRole> T getRole(final String name) {
		assert !StringHelper.isBlank(name);

		T result;
		Iterator<UserRole> iterator;
		UserRole role;

		result = null;
		if (this.roles != null) {
			iterator = this.roles.iterator();
			while (result == null && iterator.hasNext()) {
				role = iterator.next();
				result = role.getAuthorityName().equals(name) ? (T) role : null;
			}
		}

		return result;
	}

	public void addRole(final UserRole role) {
		assert role != null;
		assert !this.hasRole(role.getClass());

		if (this.roles == null) {
			this.roles = new ArrayList<UserRole>();
		}

		this.roles.add(role);
	}

	public void removeRole(final UserRole role) {
		assert role != null;
		assert this.hasRole(role);

		this.roles.remove(role);
	}

	// Other methods ----------------------------------------------------------

}