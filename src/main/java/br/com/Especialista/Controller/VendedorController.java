package br.com.Especialista.Controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.Especialista.Repositories.VendedorRepository;

@RestController
public class VendedorController {
	
	private VendedorRepository vendedorrepository;
	
	public VendedorController (VendedorRepository vendedorrepository) {
		this.vendedorrepository = vendedorrepository;
	}
	
	
	

}
