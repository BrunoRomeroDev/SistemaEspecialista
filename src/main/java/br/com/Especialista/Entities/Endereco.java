package br.com.Especialista.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Endereco",uniqueConstraints=@UniqueConstraint(columnNames={"cep"}))
@Data
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="cep")
	private Integer cep;
	@Size(max=2)
	@Column(name="uf")
	private String uf;
	@Size(max=30)
	@Column(name="localidade")
	private String localidade;
	@Size(max=50)
	@Column(name="bairro")
	private String bairro;
	@Size(max=250)
	@Column(name="logradouro")
	private String logradouro;
	@Size(max=50)
	@Column(name="complemento")
	private String complemento;

	public Endereco(EnderecoDTO enddto) {
		this.cep = Integer.parseInt(enddto.getCep().replaceAll("[^0-9]+",""));
		this.uf = enddto.getUf();	
		this.localidade = enddto.getLocalidade();		
		this.bairro = enddto.getBairro();
		this.logradouro = enddto.getLogradouro();
		this.complemento = enddto.getComplemento();
		
		
			
		}
}
