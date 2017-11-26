package br.com.carrinho.facade;

import br.com.carrinho.entity.Carrinho;

public interface CarrinhoFacade {

    /**
     * Cria um usuario
     * @param carrinho carrinho
     * @return Carrinho
     */
    Carrinho create(Carrinho carrinho);

}
