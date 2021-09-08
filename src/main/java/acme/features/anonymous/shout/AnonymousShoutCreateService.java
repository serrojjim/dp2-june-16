package acme.features.anonymous.shout;



import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.AnonymousSpamRepository;
import acme.components.Spam.Spam1;
import acme.entities.sergiolo1.Sergiolo1;
import acme.entities.shouts.Shout;
import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
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
		
		request.unbind(entity, model, "author", "text", "link","sergiolo1.sergiolo2","sergiolo1.sergiolo3","sergiolo1.sergiolo4","sergiolo1.sergiolo5");
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
			if(!entity.getAuthor().isEmpty()) {
				final boolean condition1 =  !Spam1.isSpam(entity.getAuthor(), this.spamRepository.findSpam());

				errors.state(request, condition1, "author", "anonymous.shout.form.error.author");
				
				}
				if(!entity.getText().isEmpty()) {
				final boolean condition2 =  !Spam1.isSpam(entity.getText(), this.spamRepository.findSpam());

				errors.state(request, condition2, "text", "anonymous.shout.form.error.text");
				
				}
				
				final Money budget = entity.getSergiolo1().getSergiolo4();
				
				final boolean condition3=budget.getCurrency().equals("EUR");
				final boolean condition4=budget.getCurrency().equals("USD");
				final boolean condition6=budget.getCurrency().equals("GBP");
				
				errors.state(request, condition3 || condition4 || condition6, "sergiolo1.sergiolo4", "anonymous.shout.form.error.sergiolo4");
				
				final boolean condition5=ChronoUnit.DAYS.between(LocalDateTime.now(), entity.getSergiolo1().getSergiolo3()) >= 7;
				
				errors.state(request, condition5, "sergiolo1.sergiolo3", "anonymous.shout.form.error.sergiolo3");
				
				
				
				final Collection<Sergiolo1> conditionUnique = this.shoutRepository.findAllSergiolo1();
				
				boolean condicionUnica = true;
				for(final Sergiolo1 s : conditionUnique){
					
					
					if(s.getSergiolo2().equalsIgnoreCase(entity.getSergiolo1().getSergiolo2())) {
						condicionUnica=false;
					}
				}
				
				errors.state(request, condicionUnica, "sergiolo1.sergiolo2", "anonymous.shout.form.error.sergiolo2");

				
				errors.state(request, (this.cumplePatronFechaActual(entity.getSergiolo1().getSergiolo2())), "sergiolo1.sergiolo2", "anonymous.shout.form.error.sergiolo2");
		}


		
		
	}
	
	
	public boolean cumplePatronFechaActual(final String identification) {
		
		final String atributoSplited = identification.split("/")[0];
		final int anyo = Integer.parseInt( atributoSplited.substring(0, 2));
		final int mes = Integer.parseInt( atributoSplited.substring(2, 4));
		final int dia = Integer.parseInt( atributoSplited.substring(4, 6));
	
		
		
		final LocalDate fechaDeHoy = LocalDate.now();
		final String parseado = String.valueOf(fechaDeHoy.getYear()).substring(2, 4);

		final boolean condicion3=Integer.parseInt(parseado)==anyo;
		
		
		return fechaDeHoy.getDayOfMonth()==dia && fechaDeHoy.getMonthValue()==mes && condicion3;
		
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
