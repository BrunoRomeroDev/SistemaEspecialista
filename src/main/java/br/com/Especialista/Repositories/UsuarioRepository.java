package br.com.Especialista.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

	Optional<Usuario> findByLogin(String username);

}
