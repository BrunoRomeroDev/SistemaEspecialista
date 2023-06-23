package br.com.Especialista.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Repositories.ClientesRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/especialista")
public class ControllerGen {
	
	@Autowired
	ClientesRepository clienterespository;
	
	@GetMapping("/test")
	@Operation(summary = "Consulta de teste", hidden = true)
	public String test() {
		return "Teste";
	}
	
	
	@RequestMapping(value="hello/{nome}",method = RequestMethod.GET)
	@Operation(summary = "teste hello",description = "Descrição da rota ")
	@ResponseBody
	public String helloCLiente(@PathVariable("nome") String nomeCliente) {
		return String.format("hello %s",nomeCliente);
		
	}
	
	@RequestMapping(value="erroInteger/{number}",method = RequestMethod.GET)
	@Operation(summary = "Teste de integer",description = "Descrição da rota ")
	@ResponseBody
	public Integer Inteteste(@PathVariable("number") Integer numeroRecebido) {
		return numeroRecebido ;
	}
	@RequestMapping(
			value="/postxmljson/{nome}",
			method = RequestMethod.POST, 
			consumes = {"application/json","application/xml"},
			produces = {"application/json","application/xml"})
	@ResponseBody
	public ResponseEntity<Iterable<Cliente>> clientes(@PathVariable("nome") String nomeCliente,@RequestBody Cliente cliente) {
		Iterable<Cliente> response = clienterespository.findByNomeLike(nomeCliente);
		return ResponseEntity.ok(response) ;
	}

}
