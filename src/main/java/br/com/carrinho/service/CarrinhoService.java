package br.com.carrinho.service;

import br.com.carrinho.entity.Carrinho;

public interface CarrinhoService {

    /**
     * Cria um usuario
     * @param carrinho carrinho
     * @return Carrinho
     */
    Carrinho create(Carrinho carrinho);

}
