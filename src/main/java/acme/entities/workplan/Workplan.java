package acme.entities.workplan;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
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
		@ManyToMany(mappedBy = "workplan", fetch = FetchType.EAGER, cascade = CascadeType.ALL) //Eager necesario para showWorkplan
		protected Set<Task> task;
	
	// Derived attributes
		
		public Double getTotalWorkload() {
			return this.getTask().stream().mapToDouble(Task::getWorkload).sum();
		}
		
//		public Boolean isPrivate() {
//			return this.getTask().stream().anyMatch(Task::getIsPrivate);
//		}
		
		public void addTask(final Task task) {
			this.task.add(task);
		}
		
		public List<Task> getTaskList() {
			return this.task.stream().collect(Collectors.toList());
		}

		@Override
		public String toString() {
			return "Workplan [title=" + this.title + ", executionPeriod=" + this.executionPeriod + ", isPrivate=" + this.isPrivate + 
				", userAccount=" + this.userAccount + ", task=" + this.task + ", id=" + this.id + "]";
		}
		
}
