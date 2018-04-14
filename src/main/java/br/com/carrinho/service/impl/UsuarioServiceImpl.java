package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Usuario;
import br.com.carrinho.repository.UsuarioRepository;
import br.com.carrinho.service.UsuarioService;
import br.com.carrinho.service.exception.RecurseNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario findByLogin(String login) {
        Usuario usuario = this.usuarioRepository.findByLogin(login);
        this.validate(usuario);
        return usuario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario create(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    private void validate(Usuario usuario){
        if(usuario == null){
            throw new RecurseNotFoundException("O usuário não foi encontrado", "Não foi encontrado um usuário com o login fornecido");
        }
    }

}
