package br.com.carrinho.repository;

import br.com.carrinho.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Método que busca o usuario pelo login
     * @param login login
     * @return Usuario
     */
    Usuario findByLogin(String login);

    /**
     * Método que busca o usuario pelo email
     * @param email email
     * @return Usuario
     */
    Usuario findByEmail(String email);

}