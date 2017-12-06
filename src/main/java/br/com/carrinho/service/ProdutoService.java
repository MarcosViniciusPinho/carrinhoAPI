package br.com.carrinho.service;

import br.com.carrinho.entity.Produto;
import br.com.carrinho.entity.ProdutoCarrinho;
import br.com.carrinho.repository.filter.model.ProdutoFilter;

import java.util.List;

public interface ProdutoService {

    void recuperarProdutosNoCarrinho(List<ProdutoCarrinho> produtoCarrinhoList);

    List<Produto> getProdutosFiltrados(ProdutoFilter produtoFilter);

}
