package br.com.carrinho.service;

import br.com.carrinho.entity.Usuario;

public interface UsuarioService {

    /**
     * Método que busca o usuario pelo login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

    /**
     * Método que salva um usuário
     * @param usuario usuario
     * @return Usuario
     */
    Usuario save(Usuario usuario);

}
