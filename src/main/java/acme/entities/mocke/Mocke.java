package acme.entities.mocke;

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
public class Mocke extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	protected LocalDateTime				deadline;

	@Column(unique = true)
	@NotBlank 
	@Pattern(regexp="^\\w{5}-[0-9]{2}/[0-9]{2}/[0-9]{2}$",message="pattern must be AAAAA-dd/MM/YY, must be the date of today, A must be alphanumeric")  // ^\w{2,4}:yy:mmdd$
	protected String			identification;

	@NotNull
	@Valid
	protected Money			budget;

	@NotNull
	protected Boolean			important;
	
	@OneToOne(mappedBy="mocke", cascade=CascadeType.ALL)
	protected Shout shout;

}