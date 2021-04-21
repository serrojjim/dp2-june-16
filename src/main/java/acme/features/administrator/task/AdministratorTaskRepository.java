package acme.features.administrator.task;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorTaskRepository extends AbstractRepository{

	@Query("SELECT t FROM Task t")
	List<Task> findMany();
	
	@Query("SELECT t FROM Task t WHERE t.isPrivate = true")
	List<Task> findManyPrivate();

}
