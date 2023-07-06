package br.com.Especialista.Controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.Especialista.Entities.Cliente;
import br.com.Especialista.Entities.Pedido;
import br.com.Especialista.Entities.PedidoDTO;
import br.com.Especialista.Entities.PedidoDTOVendedor;
import br.com.Especialista.Entities.PedidoStatusDTO;
import br.com.Especialista.Entities.StatusPedido;
import br.com.Especialista.Repositories.PedidosRepository;
import br.com.Especialista.Services.PedidoService;

@RestController
@RequestMapping("/especialista/pedidos")
public class PedidoController {
	
	private PedidosRepository pedidosrepository;
	private PedidoService pedidoservice;
	
	public  PedidoController(PedidosRepository pedidosrepository,
							 PedidoService pedidoservice) {
		 this.pedidosrepository = pedidosrepository;
		 this.pedidoservice = pedidoservice;

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/buscaexample")
	public ResponseEntity buscarClienteExample(Pedido filtro) {
		ExampleMatcher matcher = ExampleMatcher
									.matching()
									.withIgnoreCase()
									.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example example = Example.of(filtro,matcher);
		List<Cliente> lista = pedidosrepository.findAll(example);
		return ResponseEntity.ok(lista);
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/buscaid/{id}")
	public ResponseEntity buscaPedidoId(@PathVariable Integer id){
		return pedidoservice.buscaPedidoId(id).map( pedido -> {
			pedidoservice.buscaPedidoId(pedido.getId());
			return ResponseEntity.status(HttpStatus.OK).body(pedido);
		}).orElseThrow(()-> new ResponseStatusException(NOT_FOUND,"Pedido não encontrado"));
	}
	
	@PostMapping
	@ResponseStatus(CREATED)
	public ResponseEntity<Pedido> save(@RequestBody PedidoDTO pedido) { 
		return ResponseEntity.ok().body(pedidoservice.salvarpedido(pedido));
		
	}
	
	@PostMapping("/full")
	@ResponseStatus(CREATED)
	public Pedido saveFull(@RequestBody PedidoDTOVendedor pedido) { 
		return pedidoservice.salvarpedidoFull(pedido);
		
	}
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Pedido pedido) {
		pedidosrepository
		.findById(id)
		.map(p -> {
			pedido.setId(id);
			pedidosrepository.save(pedido);
			return pedido;
		}).orElseThrow(() -> 
			new ResponseStatusException(NOT_FOUND,"Pedido não encontrado!"));
		};
		
		@DeleteMapping("{id}")
		@ResponseStatus(NO_CONTENT)
		public void delete(@PathVariable Integer id) {
			pedidosrepository
			.findById(id)
			.map(p ->{
				pedidosrepository.delete(p);
				return Void.TYPE;
			}).orElseThrow( () -> 
			new ResponseStatusException(NOT_FOUND,"Produto não encontrado."));
			
		}
		@PatchMapping("{id}")
	
		public void updateStatus(@RequestBody PedidoStatusDTO pdto,
								 @PathVariable Integer id) {
			
			String novoStatus = pdto.getStatus();
			pedidoservice.updateStatus(id,StatusPedido.valueOf(novoStatus));
			
			
		}
		

	}

