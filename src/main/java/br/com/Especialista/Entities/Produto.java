package br.com.Especialista.Entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
	@NotEmpty(message="{campo.descricao}")
	private String descricao;
	
	@Column(name="precoUnitario")
	@NotNull(message="{campo.preco}")
	private BigDecimal preco;
	
	public Produto(String desc, BigDecimal preco) {
		this.descricao = desc;
		this.preco = preco;
		
	}
}
