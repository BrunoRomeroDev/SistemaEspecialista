package br.com.Especialista.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.Especialista.Entities.Produto;

public interface Produtos extends JpaRepository<Produto,Integer>{
	

}
