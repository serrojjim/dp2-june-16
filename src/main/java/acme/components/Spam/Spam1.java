package acme.components.Spam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.spam.Spam;
import acme.entities.spam.SpamWord;

public  class Spam1 {
	
	@Autowired
	public static AnonymousSpamRepository spamRepository;
	
	public static boolean isSpam(  final String text,  final List<Spam> spam) {
		
		boolean salida = true;
		
		final StringTokenizer st = new StringTokenizer(text);
				
		
		 final Set<SpamWord> palabras = spam.get(0).getSpamWords();
		
		 final List<SpamWord> palabras1 = new ArrayList<>();
		 
		
		for (final SpamWord x : palabras)
		     palabras1.add(x);
		
		
		
		String texto = text;
		 Double contador = 0.0;
		
		for(int i = 0; i<palabras1.size(); i++) {
			
		final String palabra = palabras1.get(i).getWord();
		
		
		while (texto.indexOf(palabra) > -1) {
		      texto = texto.substring(texto.indexOf(
		    	  palabra)+ palabra.length(),texto.length());
		      contador++; 
		}
		}
		
		if((contador/st.countTokens()) <= spam.get(0).getThreshold()) {
			salida = false;
		}
		return salida;
	}

}
