package acme.features.authenticated.manager;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedManagerRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select m from Manager m where m.userAccount.id = ?1")
	Manager findOneManagerByUserAccountId(int id);
	
	@Query("select ua from Task ua")
	Collection<Task> findAllTask();
	
	
	@Query("select ua from Task ua where ua.id = ?1 and ua.userAccount.id = ?2")
	Task findOneTaskByIdAndUA(int idTask,int idUA);
	
	
	@Query("select ua from Task ua where ua.userAccount.id = ?2")
	List<Task> findTaskByUA(int idUA);
	
	@Query("select t from Task t where t.id = ?1")
	Task findTaskById(int id);

}
