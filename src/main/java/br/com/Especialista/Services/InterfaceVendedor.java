package br.com.Especialista.Services;

import java.util.List;

import br.com.Especialista.Entities.Vendedor;

public interface InterfaceVendedor {
	
	Vendedor cadastrarVendedor(Vendedor vendedor);

	List<Vendedor> buscatodosvendedores();

	Vendedor atualizaVendedor(Vendedor vendedor);

	void deletavendedor(Integer id);
	

}
