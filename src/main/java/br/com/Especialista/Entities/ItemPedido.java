package br.com.Especialista.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name= "pedidoId")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="produtoId")
	private Produto produto;
	
	@Column(name="quantidade")
	private Integer qtd;
	
	
	public ItemPedido(Integer id) {
		this.id = id;
	}
}
