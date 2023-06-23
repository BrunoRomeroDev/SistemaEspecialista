package br.com.Especialista.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor

public class Produto {
	

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(name="descricao")
	@NotEmpty(message="Campo descrição é obrigatório")
	private String descricao;
	
	@Column(name="precoUnitario")
	@NotNull(message="Campo Preço é obrigatório")
	private BigDecimal preco;
	
	public Produto(String desc, BigDecimal preco) {
		this.descricao = desc;
		this.preco = preco;
		
	}
}
