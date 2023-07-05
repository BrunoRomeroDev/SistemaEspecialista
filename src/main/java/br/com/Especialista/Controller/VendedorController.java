package br.com.Especialista.Controller;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.Especialista.Entities.Vendedor;
import br.com.Especialista.Services.InterfaceVendedor;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/especialista/vendedor")
public class VendedorController {
	
	private InterfaceVendedor vendedorservice;
	

	public VendedorController (InterfaceVendedor vendedorservice) {
		this.vendedorservice = vendedorservice;
	}
	
	@PostMapping("/cadastrar")
	@Operation(summary="Inserir cadastro via body")
	public ResponseEntity<Vendedor> cadastroVendedor(@RequestBody Vendedor vendedor){
			if (vendedorservice.cadastrarVendedor(vendedor)==null) {
				return ResponseEntity.noContent().build();
			}
		return ResponseEntity.status(CREATED).body(vendedor);
	}
	
	@GetMapping("/buscar")
	@Operation(summary="Buscar todos os cadastros")
	public ResponseEntity<List<Vendedor>> buscatodosvendedores() {
		return ResponseEntity.status(OK).body(vendedorservice.buscatodosvendedores());
	}
	
	@PutMapping("/alterar/")
	@Operation(summary = "Atualizar Vendedor")
	public ResponseEntity<Vendedor> alterarvendedor(@RequestBody Vendedor vendedor){
			if(vendedorservice.atualizaVendedor(vendedor)==null) {
				return ResponseEntity.status(BAD_REQUEST).body(null);
			}
		return ResponseEntity.status(OK).body(vendedor);
	}
	
	@DeleteMapping("/deletar/{id}")
	@Operation(summary="Deletar Vendedor")
	public void deletavendedor(@PathVariable Integer id) {
		vendedorservice.deletavendedor(id);
	}
	
}
