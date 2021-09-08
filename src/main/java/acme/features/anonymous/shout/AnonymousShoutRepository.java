package acme.features.anonymous.shout;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import acme.entities.sergiolo1.Sergiolo1;
import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

public interface AnonymousShoutRepository extends AbstractRepository {
	
	@Query("select s from Shout s")
	Collection<Shout> findMany();
	
	@Query("select s from Sergiolo1 s")
	Collection<Sergiolo1> 	findAllSergiolo1();
	
	
	@Query("select s from Sergiolo1 s where s.sergiolo2 = ?1")
	Optional<Sergiolo1> findShoutID(String uniqueId);
	
}
