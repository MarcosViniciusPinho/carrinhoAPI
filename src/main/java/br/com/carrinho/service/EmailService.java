package br.com.carrinho.service;

import br.com.carrinho.entity.Carrinho;

public interface EmailService {

    void send(Carrinho carrinho);

}
