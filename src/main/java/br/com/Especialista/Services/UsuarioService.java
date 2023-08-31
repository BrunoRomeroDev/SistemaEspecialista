package br.com.Especialista.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.Especialista.Entities.Usuario;
import br.com.Especialista.Exception.SenhaInvalidaException;
import br.com.Especialista.Repositories.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

	
	    private PasswordEncoder encoder ;

	    @Autowired
	    private UsuarioRepository repository;

	    @Transactional
	    public Usuario salvar(Usuario usuario){
	        return repository.save(usuario);
	    }
	    
	    /**
		 * Autenticar usuarios
		 * @param Usuario class
		 * @return Detalhes do usuario com Userdetails.class
		 * 
		 */

	    public UserDetails autenticar(Usuario usuario){
	        UserDetails user = loadUserByUsername(usuario.getLogin());
	        boolean check = encoder.matches( usuario.getSenha(), user.getPassword() );

	        if(check){
	            return user;
	        }

	        throw new SenhaInvalidaException();
	    }
	    
	    /**
		 * Carregar Usuario
		 * @param String usernam com nome do usuario
		 * @return Detalhes do usuario com Userdetails.class
		 * 
		 */

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Usuario usuario = repository.findByLogin(username)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

	        String[] roles = usuario.isAdmin() ?
	                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

	        return User
	                .builder()
	                .username(usuario.getLogin())
	                .password(usuario.getSenha())
	                .roles(roles)
	                .build();
	    }

}
