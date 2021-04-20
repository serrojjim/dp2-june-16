package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface AnonymousTaskRepository extends AbstractRepository{

	@Query("select t from Task t")
	Collection<Task> findMany();

	
//
//
//	@Query("select t from Task t where t.isFinished == true")
//	Collection<Task> findNotFinished();
	
	
	}

