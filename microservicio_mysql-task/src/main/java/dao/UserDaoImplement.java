package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDaoImplement implements UserDaoInterfaz {

	@Autowired
	UserJpaSpring userSpring;

	@Override
	public void agregarUsuario(User usuario) {
		userSpring.save(usuario);

	}

	@Override
	public User recuperarUsuario(String username) {
		return userSpring.findByUsername(username);
	}

	@Override
	public void eliminarUsuario(String username) {
		userSpring.eliminarUsuario(username);

	}

	@Override
	public List<User> devolverUsuarios() {
		return userSpring.findAll();
	}

	@Override
	public void eliminarUsuario(int idUsuario) {
		userSpring.deleteById(idUsuario);

	}

	@Override
	public User recuperarUsuario(int idUsuario) {
		return userSpring.findById(idUsuario).orElse(null);
	}

	@Override
	public void actualizarUsuario(User usuario) {
		userSpring.save(usuario);

	}

	@Override
	public User recuperarUsuarioContraseña(String password) {
		return userSpring.findByPassword(password);
	}

}
