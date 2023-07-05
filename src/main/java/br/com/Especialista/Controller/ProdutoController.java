package br.com.Especialista.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.Especialista.Entities.Produto;
import br.com.Especialista.Repositories.ProdutosRepository;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping("/especialista/produto")
public class ProdutoController {

	@Autowired
	private ProdutosRepository produtosrepository;
	
	@GetMapping("/{id}")
	public Produto buscaproduto(@PathVariable Integer id) {
		return produtosrepository.findById(id).get();
	}
	
	@PostMapping("/{desc}/{preco}")
	@Operation(summary="Inserir cadastro via variavel no path")
	public ResponseEntity<Produto> novoproduto(@PathVariable("desc") String descricao, @PathVariable("preco") BigDecimal precoUnit ) {
		if(produtosrepository.save(new Produto(descricao,precoUnit)) == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Produto(descricao,precoUnit) );
		return ResponseEntity.status(HttpStatus.CREATED).body(null) ;
	}
	
	@PostMapping("/inserir/")
	@Operation(summary="Inserir cadastro no body")
	public ResponseEntity<Produto> novoprodutoBody(@RequestBody Produto prod ) {
		if(produtosrepository.save(prod) == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null );
		return ResponseEntity.status(HttpStatus.CREATED).body(prod) ;
	}
	
	@PutMapping("/")
	public ResponseEntity<Produto> atualizaProduto(@RequestBody  Produto produto) {
		if(produtosrepository.save(produto) == null)
			ResponseEntity.badRequest();
		return ResponseEntity.status(HttpStatus.OK).body(produto);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<String> deleteProduto(@PathVariable Integer id) {
		produtosrepository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Item Excluido!!");
	}

}
