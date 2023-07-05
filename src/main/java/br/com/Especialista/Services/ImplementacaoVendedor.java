package br.com.Especialista.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.Especialista.Entities.Vendedor;
import br.com.Especialista.Repositories.VendedorRepository;
import jakarta.transaction.Transactional;

@Service
public class ImplementacaoVendedor implements InterfaceVendedor{
	
		@Autowired
		private VendedorRepository vendedorrepository;

		@Override
		@Transactional
		public Vendedor cadastrarVendedor(Vendedor vendedor) {
			return vendedorrepository.save(vendedor);
		}
		
		@Override
		@Transactional
		public List<Vendedor> buscatodosvendedores(){
			return vendedorrepository.findAll();
		}
		@Transactional
		@Override
		public Vendedor atualizaVendedor(Vendedor vendedor) {
				return vendedorrepository.save(vendedor);
		}
		
		@Transactional
		@Override
		public void deletavendedor(Integer id) {
			vendedorrepository.deleteById(id);
		}
		
	
}
