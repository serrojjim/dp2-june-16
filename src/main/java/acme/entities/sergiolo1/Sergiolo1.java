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
public class Sergiolo1 extends DomainEntity {

	protected static final long	serialVersionUID	= 1L;

	@NotNull
	protected LocalDateTime				sergiolo3;

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp="^\\w{2,4}:[0-9]{2}:[0-9]{4}$",message="pattern must be AAAA:yy:mmdd, must be the date of today")  // ^\w{2,4}:yy:mmdd$
	protected String			sergiolo2;

	@NotNull
	@Valid
	protected Money			sergiolo4;

	@NotNull
	protected Boolean			sergiolo5;
	
	@OneToOne(mappedBy="sergiolo1", cascade=CascadeType.ALL)
	protected Shout shout;

}