package br.com.Especialista.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.Endereco;
import br.com.Especialista.Entities.EnderecoDTO;
import br.com.Especialista.Feign.CepFeign;
import br.com.Especialista.Repositories.ClientesRepository;
import br.com.Especialista.Repositories.EnderecoRepository;

@RestController
@RequestMapping("/especialista/cliente")
public class ClienteController {
	
	@Autowired
	private ClientesRepository clienterepository;
	
	@Autowired
	private CepFeign cepfeign;
	@Autowired
	private EnderecoRepository enderecorepository;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente>  buscarCliente(@PathVariable Integer id) {
		Optional<Cliente> cliente = clienterepository.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/buscaexample")
	public ResponseEntity buscarClienteExample(Cliente filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro,matcher);
		List<Cliente> lista = clienterepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/{nome}")
	public ResponseEntity<Cliente> inserirCliente(@PathVariable String nome){
			Cliente cli = new Cliente(nome);
			clienterepository.save(cli);
		return ResponseEntity.ok(cli);
	}
	
	@PostMapping("/inserir")
	public ResponseEntity<Cliente> inserirClienteBody(@RequestBody @Validated Cliente cli){
		EnderecoDTO end = cepfeign.getEnderecoPorCep(cli.getCep().getCep());
		cli.setCep(enderecorepository.save(new Endereco(end)));
		if(clienterepository.save(cli) == null)
				ResponseEntity.badRequest();
		return ResponseEntity.status(HttpStatus.OK).body(cli);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("/alterar/{id}")
	public ResponseEntity alterarcliente(@PathVariable Integer id,@RequestBody Cliente cli){
		
	return clienterepository
			.findById(id)
			.map( cliente -> {
				cli.setId(cliente.getId());
				clienterepository.save(cli);
				return ResponseEntity.noContent().build();
			}).orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletarCLiente(@PathVariable Integer id){
		Optional<Cliente> cli = clienterepository.findById(id);
		if(cli.isPresent()) {
			clienterepository.deleteById(id);
			ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
