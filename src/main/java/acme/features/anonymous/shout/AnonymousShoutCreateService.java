package acme.features.anonymous.shout;



import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {
	
	@Autowired
	protected AnonymousShoutRepository shoutRepository;
	
	@Autowired
	protected AnonymousSpamRepository spamRepository;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors);
	}
	
	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "author", "text", "link","mocke.identification","mocke.deadline", "mocke.budget","mocke.important");
	}
	
	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;
		
		Shout result;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		
		result = new Shout();
		result.setAuthor("");
		result.setText("");
		result.setMoment(moment);
		result.setLink("");
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors()) {
			
			final boolean condition3 =  !entity.getMocke().getBudget().getCurrency().equals("EUR") && !entity.getMocke().getBudget().getCurrency().equals("USD")  && !entity.getMocke().getBudget().getCurrency().equals("GBP") ;

			errors.state(request, !condition3, "mocke.budget", "anonymous.shout.form.error.budget");

			final String[] parts2 =entity.getMocke().getIdentification().split("-"); //espliteopor - y obtendo dos partes
			final String[] parts =parts2[1].split("/"); //espliteopor / y obtendo dia,mes y año
			//AAAAA-dd/MM/YY
			final int dia = Integer.parseInt(parts[0]);
			final int mes = Integer.parseInt(parts[1]);
			final int ano = Integer.parseInt(parts[2]);

			final int mesactual = LocalDateTime.now().getMonthValue();
			final int diaactual = LocalDateTime.now().getDayOfMonth();

			final boolean condition4 = mes== mesactual;
			
			final boolean condition5 = dia == diaactual;
			
			final String anoactual =String.valueOf(LocalDateTime.now().getYear());
			final int anoactual2digits  = Integer.parseInt(anoactual.substring(2));
			final boolean condition6 =ano==anoactual2digits;

			errors.state(request, condition4, "mocke.identification", "anonymous.shout.form.error.formatomes");
			errors.state(request, condition5, "mocke.identification", "anonymous.shout.form.error.formatodia");
			errors.state(request, condition6, "mocke.identification", "anonymous.shout.form.error.formatoaño");

			
			final LocalDateTime fechaactual = LocalDateTime.now(); //Fecha de creación de shout
			final LocalDateTime deadlineentrada = entity.getMocke().getDeadline();
			
			
			final boolean condition7 = ChronoUnit.DAYS.between(fechaactual, deadlineentrada)>=7;

			errors.state(request, condition7, "mocke.deadline", "anonymous.shout.form.error.deadline");	
			
			final boolean esunico = this.shoutRepository.obtenerIdentification().stream().noneMatch(x -> x.equalsIgnoreCase(entity.getMocke().getIdentification()));
			errors.state(request, esunico, "mocke.identification", "Ya está en uso, utiliza uno diferente");
			
		if(!entity.getAuthor().isEmpty()) {
		final boolean condition1 =  !Spam1.isSpam(entity.getAuthor(), this.spamRepository.findSpam());

		errors.state(request, condition1, "author", "anonymous.shout.form.error.author");
		
		}
		if(!entity.getText().isEmpty()) {
		final boolean condition2 =  !Spam1.isSpam(entity.getText(), this.spamRepository.findSpam());

		errors.state(request, condition2, "text", "anonymous.shout.form.error.text");
		
		}
		}
		
	}
	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;
		Date moment;
		
		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		final List<Spam> spam = this.spamRepository.findSpam();
		
		final boolean salida = Spam1.isSpam(entity.getText(),  spam);
		
		if(!salida) {
		this.shoutRepository.save(entity);
		}
		
		
	}
	
	
	


}
