package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Usuario;
import br.com.carrinho.repository.UsuarioRepository;
import br.com.carrinho.service.UsuarioService;
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
        return this.usuarioRepository.findByLogin(login);
    }

}
