package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{

	@Query("select t from Task t")
	Collection<Task> findMany();

	

	
	
	}

