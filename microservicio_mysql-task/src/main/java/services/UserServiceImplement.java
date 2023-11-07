package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDaoInterfaz;
import model.User;

@Service
public class UserServiceImplement implements userService {

	@Autowired
	UserDaoInterfaz userDao;

	@Override
	public boolean agregarUsuario(User usuario) {
		List<User> usuarios = recuperarUsuarios();

		// Verificar si el usuario ya existe en la lista de usuarios
		for (User u : usuarios) {
			if (u.getUserId() == usuario.getUserId()) {
				return false; // El usuario ya existe, no se puede agregar de nuevo
			}
		}

		// Si el usuario no existe en la lista, agregarlo a la base de datos
		userDao.agregarUsuario(usuario);
		return true;
	}

	@Override
	public List<User> recuperarUsuarios() {
		return userDao.devolverUsuarios();
	}

	@Override
	public void actualizarUsuario(User usuario) {
		if (userDao.recuperarUsuario(usuario.getUserId()) != null) {
			userDao.actualizarUsuario(usuario);
		}
	}

	@Override
	public boolean eliminarUsuario(int idUsuario) {
		if (userDao.recuperarUsuario(idUsuario) != null) {
			userDao.eliminarUsuario(idUsuario);
			return true;
		}
		return false;
	}

	@Override
	public User buscarUsuario(int idUsuario) {
		return userDao.recuperarUsuario(idUsuario);
	}

	@Override
	public String buscarUsuarioContraseñaUsername(String password, String username) {
		List<User> usuariosList = userDao.devolverUsuarios();

		// Por alguna razon los valores de username y password llegan trocados, por eso
		// cambio el orden abajo

		// Buscamos si existe el usuario en la lista y si tiene la contraseña
		for (User u : usuariosList) {
			if (u.getPassword().equals(username) && u.getUsername().equals(password)) {
				// La contraseña está en la lista
				return String.valueOf(u.getUserId());
			}
		}

		// Si llegamos aquí, no se encontró un usuario válido
		return "false";
	}

}
