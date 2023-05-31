package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.Especialista.Entities.Cliente;

@Repository
public interface Clientes extends JpaRepository<Cliente,Integer>{

	

	Iterable<Cliente> findByNomeLike(String string);
	
		
}
