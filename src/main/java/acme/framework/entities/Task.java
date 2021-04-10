package acme.framework.entities;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.ExecutionPeriod;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task extends DomainEntity{



	protected static final long	serialVersionUID	= 1L;

	
	// Attributes -------------------------------------------------------------

		@NotBlank
		@Length(min = 1, max = 80)
		protected String			title;

		@NotBlank
		@Length(min = 1, max = 500)
		protected String			description;

		
		@NotNull
		protected Double			workload;

		@NotNull
		protected ExecutionPeriod executionPeriod;
		
		
		@URL
		protected String			url;

		@NotNull
		protected boolean isPrivate;


		

}
