package br.com.Especialista.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

//@Entity
//@Data
public class ItemPedido {
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	//@Column(name="pedidoId")
	//@ManyToMany
	private Pedido pedido;
	//@Column(name="produtoId")
	private Produto produto;
	//@Column(name="quantidade")
	private Integer qtd;
	
	

}
