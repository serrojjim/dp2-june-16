package acme.features.anonymous.workplan;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.workplan.Workplan;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/workplan")
public class AnonymousWorkPlanController extends AbstractController<Anonymous, Workplan>{
	
	@Autowired
	private AnonymousWorkPlanListServiceExecutionPeriod workPlanService;
	
	@Autowired
	private AnonymousWorkPlanShowService showService;
	
	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.workPlanService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}