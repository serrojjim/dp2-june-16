package acme.features.anonymous.userAccount;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;
import acme.framework.entities.UserAccount;

@Controller
@RequestMapping("/anonymous/user-account/")
public class AnonymousUserAccountController extends AbstractController<Anonymous, UserAccount> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousUserAccountCreateService createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
