package br.com.Especialista.Repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.Especialista.Entities.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class Clientes {
	
	
	@Autowired
	private EntityManager entitymanager;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		entitymanager.persist(cliente);
		return cliente;
	}
	
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		entitymanager.merge(cliente);
		return cliente;
	}
	@Transactional
	public Cliente deletar(Cliente cliente) {
		if(!entitymanager.contains(cliente)) {
			cliente = entitymanager.merge(cliente);
		}
		entitymanager.remove(cliente);
		return cliente;
	}
	@Transactional
	public void deletar(Integer id) {
		Cliente cliente = entitymanager.find(Cliente.class, id);
		entitymanager.remove(cliente);
		
	}
	
	@Transactional
	public List<Cliente> buscarPorNome(String nome) {
		String jpql =  "select c from Cliente c where c.nome like :nome";
		TypedQuery<Cliente> query = entitymanager.createQuery(jpql, Cliente.class);
		query.setParameter("nome","%"+nome+"%");
		return query.getResultList();
	}
	
	
	@Transactional
	public List<Cliente> obterTodos(){
		return entitymanager.createQuery("from Cliente", Cliente.class).getResultList();
			}
	
}
