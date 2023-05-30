package br.com.Especialista.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.com.Especialista.Entities.Cliente;

@Repository
public class Clientes {
	
	private static final String SELECT_ALL = "Select * from Cliente";
	private static final String INSERT = "insert into Cliente (nome) values(?)";
	private static final String UPDATE = "update Cliente set nome = ? where id = ?";
	private static final String DELETE = "delete from Cliente where id = ?";
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public Cliente salvar(Cliente cliente) {
		jdbctemplate.update(INSERT,new Object[]{ cliente.getNome()});
		return cliente;
	}
	
	public List<Cliente> obterTodos(){
		return jdbctemplate.query(SELECT_ALL, new RowMapper<Cliente>(){
			@Override
			public Cliente mapRow(ResultSet resultset, int i) throws SQLException{
				Integer id =resultset.getInt("id");
				String nome = resultset.getString("nome");
				return new Cliente(id,nome);
			}
		});
		
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbctemplate.update(UPDATE,new Object[]{ cliente.getNome(),cliente.getId()});
		return cliente;
	}
	
	public Cliente deletar(Cliente cliente) {
		jdbctemplate.update(DELETE,new Object[]{ cliente.getId()});
		return cliente;
	}
	public void deletar(Integer id) {
		jdbctemplate.update(DELETE,new Object[]{ id});
		
	}
	
	@SuppressWarnings("deprecation")
	public List<Cliente> buscarPorNome(String nome) {
		return jdbctemplate.query(
							SELECT_ALL.concat(" where nome like ? "),
							new Object[]{"%"+nome+"%"},
							obterClienteMapper());
	}
	
	public RowMapper<Cliente> obterClienteMapper(){
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultset, int i) throws SQLException{
					Integer id =resultset.getInt("id");
					String nome = resultset.getString("nome");
					return new Cliente(id,nome);	
					
			}
		};
	}
}
