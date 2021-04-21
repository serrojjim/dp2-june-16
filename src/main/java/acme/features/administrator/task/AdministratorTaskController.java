package acme.features.administrator.task;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.task.Task;
import acme.framework.components.BasicCommand;
import acme.framework.components.Model;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/task/")
public class AdministratorTaskController extends AbstractController<Administrator, Task>{
	
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorTaskService	taskService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.taskService);
		}
		
		
		
		@GetMapping("dashboard")
		public String dashboard(final Model model) {
//			this.taskService.statistics(model);
			return "administrator/task/dashboard";
		}
		
}
