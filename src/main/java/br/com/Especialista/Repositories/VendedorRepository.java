package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Vendedor;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface VendedorRepository extends JpaRepository<Vendedor,Integer>{
	

}
