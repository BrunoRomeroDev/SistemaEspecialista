package br.com.Especialista.Entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(max=100)
	private String nome;
	
	@OneToMany(mappedBy = "clientes",fetch = FetchType.LAZY)
	private Set<Pedido> pedidos;
	
	@Column(name="endereco")
	private String endereco;
	
	@Size(max = 11)
	@Column(name="cpf")
	private String CPF;
	

	public Cliente(String nome) {
		this.nome = nome;
	}

	public Cliente(Integer id,String nome) {
		this.id = id;
		this.nome = nome;
	}
	


}
