package br.com.carrinho.service;

import br.com.carrinho.entity.ProdutoCarrinho;

import java.util.List;

public interface ProdutoService {

    void recuperarProdutosNoCarrinho(List<ProdutoCarrinho> produtoCarrinhoList);

}
