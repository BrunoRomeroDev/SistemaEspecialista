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
	
	public void listarProdutosPorPreco() {
		produtorepository.findByPrecoLessThan(new BigDecimal(100.00));
	}
	
	public void listarProdutoPorDescricao() {
		produtorepository.findByDescricaoLike("lam%").forEach(System.out::println);
	}
	
	public void listarProdutoPorDescricaoOrdenado() {
		produtorepository.findByDescricaoLike("%o%", Sort.by("descricao")).forEach(System.out::println);
	}
	
	public void listarProdutoPorDescricaoPaginado() {
		Pageable pageable = PageRequest.of(0,2);
		produtorepository.findByDescricaoLike("%o%", pageable)
						 .forEach(System.out::println);
	}
	
	public void listaProdutoIniciaCom() {
		produtorepository.findByDescricaoStartingWith("s").forEach(System.out::println);
	}
	
	public void listaProdutoTerminaCom() {
		produtorepository.findByDescricaoEndingWith("a").forEach(System.out::println);
	}
	
	public void listarProdutosBydescricaoSpec() {
		Specification<Produto> spec = Produtospecs.descriEqual("led");
		produtorepository.findAll(spec).forEach(System.out::println);
	}
	
	public void listarProdutosMaiorQue() {
		Specification<Produto> spec = Produtospecs.precoGreaterThan(new BigDecimal(123.3));
		produtorepository.findAll(spec).forEach(System.out::println);
	}
	
	public void buscaPorDescricao() {
		produtorepository.buscaPorDescricao("LED").forEach(System.out::println);
	}
	
}
