package acme.entities.sergiolo1;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.entities.shouts.Shout;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sergiolo1 extends DomainEntity{



	protected static final long	serialVersionUID	= 1L;

	
	// Attributes -------------------------------------------------------------
		@Column(unique=true)
		@NotBlank
		@Pattern(regexp="^\\d{6}/\\d{2}/\\w{2}$", message="Match error yymmdd/xx/xx") // FEHCA EN FORMATO ^yymmdd/\d{2}/\w{2}$” 
		protected String			sergiolo2;

		
		@NotNull
		protected LocalDateTime			sergiolo3;
		
		@NotNull
		@Valid
		protected Money			sergiolo4;
		
		@NotNull
		protected Boolean			sergiolo5;
		
		
		@Valid
		@OneToOne(mappedBy = "sergiolo1",cascade = CascadeType.ALL) 
		protected Shout shout;
		
}
