package acme.features.administrator.workplan;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorWorkplanRepository extends AbstractRepository{

	@Query("SELECT w FROM Workplan w")
	List<Workplan> findMany();

}
