package br.com.carrinho.facade.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.entity.Usuario;
import br.com.carrinho.facade.CarrinhoFacade;
import br.com.carrinho.service.CarrinhoService;
import br.com.carrinho.service.EmailService;
import br.com.carrinho.service.ProdutoService;
import br.com.carrinho.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoFacadeImpl implements CarrinhoFacade{

    @Autowired
    private CarrinhoService carrinhoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EmailService emailService;

    @Override
    public Carrinho create(Carrinho carrinho) {
        Usuario usuario = this.usuarioService.findByLogin(carrinho.getUsuario().getLogin());
        carrinho.setUsuario(usuario);
        this.produtoService.recuperarProdutosNoCarrinho(carrinho.getProdutoCarrinhoList());
        Carrinho carrinhoSalvo = this.carrinhoService.create(carrinho);
        this.emailService.send(carrinhoSalvo);
        return carrinhoSalvo;
    }
}
