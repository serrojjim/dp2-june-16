package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Shout extends DomainEntity{
	
	protected static final long serialVersionUID = 1L;

@Temporal(TemporalType.TIMESTAMP)
@Past
@NotNull
protected Date creationMoment;

@NotBlank
@Min(5)
@Max(25)
protected String author;

@NotBlank
@Max(100)
protected String text;

@URL
protected String link;

@Override
public String toString() {
	return "Shout [creationMoment=" + this.creationMoment + ", author=" + this.author + ", text=" + this.text + ", link=" + this.link + "]";
}


}
