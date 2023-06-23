package br.com.Especialista.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.Especialista.Entities.Cliente;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface ClientesRepository extends JpaRepository<Cliente,Integer>{

	Iterable<Cliente> findByNomeLike(String string);
	
	List<Cliente> findByNomeOrId(String nome, Integer id);
	
	List<Cliente> findByNomeOrIdOrderById(String nome, Integer id);

	boolean existsByNome(String nome);
	
	@Query(value = "select c from Cliente c where c.nome like :nome ")
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Query(value = "select * from Cliente c where c.nome like :nome ", nativeQuery = true)
	List<Cliente> encontrarPorNomeNative(@Param("nome") String nome);
	
	@Query(value = "update  Cliente c set nome = :nome where id = :id")
	@Modifying
	Cliente  alterarNome(@Param("nome") String nome, @Param("id") Integer id);
	
	@Query("delete from Cliente c where c.nome = :nome")
	@Modifying
	void deleteByNome(String nome);
	
	@Query("select c from Cliente c left join fetch c.pedidos  where c.id = :id ")
	Cliente findClienteFetchPedidos(@Param("id") Integer id);
	
}
