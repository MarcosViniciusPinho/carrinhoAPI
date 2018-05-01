package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Usuario;
import br.com.carrinho.repository.UsuarioRepository;
import br.com.carrinho.service.UsuarioService;
import br.com.carrinho.service.exception.RecurseNotFoundException;
import br.com.carrinho.service.exception.UniqueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario findByLogin(String login) {
        Optional<Usuario> usuario = this.usuarioRepository.findByLogin(login);
        this.validateOnSearch(usuario);
        return usuario.get();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario create(Usuario usuario) {
        this.validadeOnSave(usuario);
        this.encodarSenha(usuario);
        return this.usuarioRepository.save(usuario);
    }

    private void encodarSenha(final Usuario usuario) {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
    }

    private void validadeOnSave(Usuario usuario) {
        this.validateLoginUnique(usuario);
        this.validateEmailUnique(usuario);
    }

    private void validateLoginUnique(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(usuario.getLogin());
        this.throwUniqueException(usuarioOptional, usuario, "Login");
    }

    private void validateEmailUnique(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.getEmail());
        this.throwUniqueException(usuarioOptional, usuario, "E-mail");
    }

    private void throwUniqueException(Optional<Usuario> usuarioOptional, Usuario usuario, String atributo) {
        if(usuarioOptional.isPresent() && usuarioOptional.get().equals(usuario)) {
            String mensagemClient = String.format("Já existe um usuário com o %s informado", atributo);
            String mensagemException = String.format("%s duplicado", atributo);
            throw new UniqueException(mensagemClient, mensagemException);
        }
    }

    private void validateOnSearch(Optional<Usuario> usuario){
        if(!usuario.isPresent()){
            throw new RecurseNotFoundException("O usuário não foi encontrado", "Não foi encontrado um usuário com o login fornecido");
        }
    }

}
