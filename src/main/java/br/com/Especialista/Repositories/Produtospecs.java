package br.com.Especialista.Repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import br.com.Especialista.Entities.Produto;

public abstract class Produtospecs {
	
	public static Specification<Produto>  descriEqual(String descricao){
		return (root,query,cb) -> cb.equal(root.get("descricao"),descricao);
	}
	
	public static Specification<Produto>  precoGreaterThan(BigDecimal preco){
		return (root,query,cb) -> cb.greaterThan(root.get("preco"),preco);
	}

}
