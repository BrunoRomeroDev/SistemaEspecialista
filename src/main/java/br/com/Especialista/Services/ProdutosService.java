package br.com.Especialista.Services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.Especialista.Entities.Produto;
import br.com.Especialista.Repositories.ProdutosRepository;
import br.com.Especialista.Repositories.Produtospecs;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtorepository;
	
	void listarProdutosPorPreco() {
		produtorepository.findByPrecoLessThan(new BigDecimal(100.00));
	}
	
	void listarProdutoPorDescricao() {
		produtorepository.findByDescricaoLike("Prod%").forEach(System.out::println);
	}
	
	void listarProdutoPorDescricaoOrdenado() {
		produtorepository.findByDescricaoLike("Prod%", Sort.by("descricao")).forEach(System.out::println);
	}
	
//	void listarProdutoPorDescricaoPaginado() {
//		Pageable pageable = PageRequest.of(0,10);
//		produtorepository.findByDescricaoLike("Prod%", pageable)
//						 .forEach(System.out::println);
//	}
	
	void listaProdutoIniciaCom() {
		produtorepository.findByDescricaoStartingWith("a").forEach(System.out::println);
	}
	
	void listaProdutoTerminaCom() {
		produtorepository.findByDescricaoEndingWith("a").forEach(System.out::println);
	}
	
	public void listarProdutosBydescricaoSpec() {
		Specification<Produto> spec = Produtospecs.descriEqual("prod");
		produtorepository.findAll(spec).forEach(System.out::println);
	}
	
}
