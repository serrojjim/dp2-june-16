package acme.framework.entities;


import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.datatypes.ExecutionPeriod;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Workplan extends DomainEntity{



	protected static final long	serialVersionUID	= 1L;

	
	// Attributes -------------------------------------------------------------

		@NotBlank
		protected String			title;
		
		@NotNull
		protected ExecutionPeriod 	executionPeriod;
		
		@NotNull
		protected boolean 			isPrivate;


		

}
