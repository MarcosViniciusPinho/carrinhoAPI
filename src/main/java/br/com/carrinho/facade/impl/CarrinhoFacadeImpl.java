package br.com.carrinho.facade.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.entity.Usuario;
import br.com.carrinho.facade.CarrinhoFacade;
import br.com.carrinho.service.CarrinhoService;
import br.com.carrinho.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoFacadeImpl implements CarrinhoFacade{

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Carrinho create(Carrinho carrinho) {
        Usuario usuario = this.usuarioService.findByLogin(carrinho.getUsuario().getLogin());
        carrinho.setUsuario(usuario);
        return this.carrinhoService.create(carrinho);
    }
}
