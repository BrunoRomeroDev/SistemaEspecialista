package br.com.Especialista.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.Especialista.Entities.Cliente;


public interface Clientes extends JpaRepository<Cliente,Integer>{

	Iterable<Cliente> findByNomeLike(String string);
	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

	boolean existsByNome(String nome);
	
	@Query(value = "select c from Cliente c where c.nome like :nome")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Query(value = "select * from Cliente c where c.nome like :nome", nativeQuery = true)
	List<Cliente> encontrarPorNomeNative(@Param("nome") String nome);
	
	@Query(value = "update  Cliente c set nome = :nomenew where id = :id")
	@Modifying
	Cliente  alterarNome();
	
	@Query("delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
}
