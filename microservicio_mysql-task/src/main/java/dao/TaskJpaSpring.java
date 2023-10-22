package dao;



import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import model.Task;

public interface TaskJpaSpring extends JpaRepository<Task, Integer> {
	
	// implementar metodos específicos que no vienen en spring jpa
	// metodo para encontrar una tarea por su titulo
	Task findByTitle(String title);
	
	// eliminar una tarea por su titulo
	@Transactional
	@Modifying
	@Query("Delete from Task t where t.title=:titulo")
	void eliminarTask(String titulo);
	
	

}// interfaz
