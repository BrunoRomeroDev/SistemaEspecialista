package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Produto;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface ProdutosRepository extends JpaRepository<Produto,Integer>{

		
}
