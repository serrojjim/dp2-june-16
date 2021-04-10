/*
 * Money.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.framework.datatypes;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ExecutionPeriod extends DomainDatatype {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@NotNull
	@Future
	protected LocalDateTime		initialDate;

	@NotNull
	@Future
	protected LocalDateTime			finalDate;

	

	// Object interface -------------------------------------------------------

	@Override
	public String toString() {
		return "ExecutionPeriod [initialDate=" + this.initialDate + ", finalDate=" + this.finalDate + "]";
	}


}