package br.com.Especialista.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.Especialista.Entities.EnderecoDTO;

@Component
@FeignClient(name="endereco",url="https://viacep.com.br/")
public interface CepFeign {

	
	@GetMapping(value="/ws/{cep}/json/")
	EnderecoDTO getEnderecoPorCep(@PathVariable("cep") Integer cep );
}
