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

package acme.datatypes;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.DomainDatatype;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ExecutionPeriod extends DomainDatatype implements Comparable<ExecutionPeriod> {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------

	@Future
	@NotNull
	protected LocalDateTime initialDate;

	@Future
	@NotNull
	protected LocalDateTime finalDate;

	// Derived attributes -----------------------------------------------------

	@Transient
	public long getWorkloadHours() {
		return ChronoUnit.DAYS.between(this.finalDate, this.initialDate);
	}

	@Override
	public int compareTo(final ExecutionPeriod o) {
		return (int) (this.getWorkloadHours() - o.getWorkloadHours());
	}

}
