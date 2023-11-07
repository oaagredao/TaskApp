package dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import model.User;

public interface UserJpaSpring extends JpaRepository<User, Integer> {

	// implementar metodos que no están en jpa

	// buscar usuario por nombre
	User findByUsername(String username);

	// buscar usuario pot contraseña
	User findByPassword(String password);

	// eliminar usuario por nombre
	@Transactional
	@Modifying
	@Query("Delete from User u Where u.username=:username")
	void eliminarUsuario(String username);

}
