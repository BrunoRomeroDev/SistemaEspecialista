package br.com.Especialista.Entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="clienteId",insertable=false, updatable=false)
	private Cliente clientes;
	
	@Column(name="dataPedido")
	private LocalDate dataPedido;

	@Column(name="total",length=100,precision=2)
	private BigDecimal total;
	

}
