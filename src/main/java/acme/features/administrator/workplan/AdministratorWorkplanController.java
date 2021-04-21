package acme.features.administrator.workplan;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.components.Model;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/workplan/")
public class AdministratorWorkplanController extends AbstractController<Administrator, Workplan>{
	
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected AdministratorWorkplanService	workplanService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
			super.addBasicCommand(BasicCommand.LIST, this.workplanService);
		}
		
		
		
		@GetMapping("dashboard")
		public String dashboard(final Model model) {
//			this.workplanService.statistics(model);
			return "administrator/workplan/dashboard";
		}
		
		@GetMapping("chart")
		public String chart(final Model model) {
			this.workplanService.chart(model);
			return "administrator/workplan/chart";
		}
		
}
