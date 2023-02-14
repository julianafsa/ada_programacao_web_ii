package br.com.ada.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ada.bookstore.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	//@Query(value="SELECT u FROM Usuarios u WHERE UPPER(u.username) = UPPER(:username)")
	Optional<Usuario> findByUsername(String username);
}
