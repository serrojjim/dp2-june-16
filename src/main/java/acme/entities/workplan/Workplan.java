package acme.entities.workplan;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.datatypes.ExecutionPeriod;
import acme.entities.task.Task;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
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
		protected Boolean 			isPrivate; // Dejarla como atributo derivado ?
		
		@Valid
		@ManyToOne
		protected UserAccount userAccount;

		@NotNull
		@Valid
		@ManyToMany(mappedBy = "workplan", fetch = FetchType.EAGER) //Eager necesario para showWorkplan
		protected List<Task> task;
	
	// Derived attributes
		
		public Double getTotalWorkload() {
			return this.getTask().stream().mapToDouble(Task::getWorkload).sum();
		}
		
//		public Boolean isPrivate() {
//			return this.getTask().stream().anyMatch(Task::getIsPrivate);
//		}
		

}
