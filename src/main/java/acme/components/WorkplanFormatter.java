package acme.components;



import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import acme.entities.workplan.Workplan;
import acme.framework.helpers.StringHelper;

public class WorkplanFormatter implements Formatter<Workplan>{
	
	@Override
	public String print(final Workplan object, final Locale locale) {
		return null;
	}

	@Override
	public Workplan parse(final String text, final Locale locale) throws ParseException {
		assert !StringHelper.isBlank(text);
		assert locale != null;
		
		final Workplan result = null;
		
		return result;
	}

}
