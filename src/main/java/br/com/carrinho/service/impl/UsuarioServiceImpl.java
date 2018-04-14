package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Usuario;
import br.com.carrinho.repository.UsuarioRepository;
import br.com.carrinho.service.UsuarioService;
import br.com.carrinho.service.exception.RecurseNotFoundException;
import br.com.carrinho.service.exception.UniqueException;
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
        this.validateOnSearch(usuario);
        return usuario;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario create(Usuario usuario) {
        this.validadeOnSave(usuario);
        return this.usuarioRepository.save(usuario);
    }

    private void validadeOnSave(Usuario usuario) {
        this.validateLoginUnique(usuario);
        this.validateEmailUnique(usuario);
    }

    private void validateLoginUnique(Usuario usuario) {
        Usuario usuarioSalvo = this.usuarioRepository.findByLogin(usuario.getLogin());
        if(usuarioSalvo != null && usuarioSalvo.equals(usuario)) {
            throw new UniqueException("Já existe um usuário com o login informado", "Login duplicado");
        }
    }

    private void validateEmailUnique(Usuario usuario) {
        Usuario usuarioSalvo = this.usuarioRepository.findByEmail(usuario.getEmail());
        if(usuarioSalvo != null && usuarioSalvo.equals(usuario)) {
            throw new UniqueException("Já existe um usuário com o e-mail informado", "E-mail duplicado");
        }
    }

    private void validateOnSearch(Usuario usuario){
        if(usuario == null){
            throw new RecurseNotFoundException("O usuário não foi encontrado", "Não foi encontrado um usuário com o login fornecido");
        }
    }

}
