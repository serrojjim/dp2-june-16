package acme.features.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.task.Task;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
public class AdministratorTaskController extends AbstractController<Administrator, Task>{
	
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorTaskService	taskService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.taskService);
		}
}
