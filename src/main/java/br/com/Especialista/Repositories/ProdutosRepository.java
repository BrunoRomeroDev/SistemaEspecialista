package br.com.Especialista.Repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.Especialista.Entities.Produto;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public interface ProdutosRepository extends JpaRepository<Produto,Integer>{
	
	List<Produto> findByDescricao(String descricao);
	
	List<Produto> findByDescricaoLike(String descricao);
	
	@Query("select c from Produto c where upper(c.descricao) like upper(?1)")
	List<Produto> findByDescricaoLike(String descricao, Sort sort);
	
	@Query("Select c from Produto c where upper(c.descricao) like upper(?1)")
	Page<Produto> findByDescricaoLike(String descricao, Pageable pageable);
	
	List<Produto> findByDescricaoStartingWith(String descricao);
	
	List<Produto> findByDescricaoEndingWith(String descricao);
	
	List<Produto> findByDescricaoContaining(String descricao);
	
	List<Produto> findByPreco(BigDecimal preco);
	
	List<Produto> findByPrecoLessThan(BigDecimal preco);
	
	List<Produto> findByPrecoGreaterThan(Long preco);
	
	List<Produto> findByPrecoLessThanEqual(BigDecimal preco);
	
	List<Produto> findByPrecoLessThanAndDescricaoLike(BigDecimal preco, String descricao);

	List<Produto> findAll(Specification<Produto> spec);

	@Query(value = "Select * from Produto prod where prod.descricao like :descricao",nativeQuery = true)
	List<Produto> buscaPorDescricao(@Param("descricao") String descricao);
		
}
