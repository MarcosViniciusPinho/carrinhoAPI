package br.com.carrinho.facade.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.entity.Endereco;
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
        carrinho.setUsuario(this.getUsuarioComEndereco(carrinho));
        this.produtoService.recuperarProdutosNoCarrinho(carrinho.getProdutoCarrinhoList());
        Carrinho carrinhoSalvo = this.carrinhoService.create(carrinho);
        this.emailService.send(carrinhoSalvo);
        return carrinhoSalvo;
    }

    private Usuario getUsuarioComEndereco(Carrinho carrinho){
        Endereco endereco = carrinho.getUsuario().getEndereco();
        Usuario usuario = this.usuarioService.findByLogin(carrinho.getUsuario().getLogin());
        usuario.setEndereco(endereco);
        return usuario;
    }
}
