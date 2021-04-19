package acme.features.authenticated.task;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface AuthenticatedTaskRepository extends AbstractRepository{

	@Query("select t from Task t")
	Collection<Task> findMany();

	@Query("select t from Task t where t.userAccount.id = ?1")
	List<Task> findAllTaskByUserAccountId(int id);
	
	@Query("select t from Task t where t.userAccount.id = ?1")
	Task findOneTaskByUserAccountId(int id);
	
	
	
	@Query("select t from Task t where t.id = ?1")	
	Task findOneTaskById(int id);
	
	}

