package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Produto;
import br.com.carrinho.entity.ProdutoCarrinho;
import br.com.carrinho.repository.ProdutoRepository;
import br.com.carrinho.repository.filter.ProdutoFilterRepository;
import br.com.carrinho.repository.filter.model.ProdutoFilter;
import br.com.carrinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoFilterRepository produtoFilterRepository;

    @Override
    public void recuperarProdutosNoCarrinho(List<ProdutoCarrinho> produtoCarrinhoList) {
         produtoCarrinhoList.forEach(
                produtoCarrinho ->
                        produtoCarrinho.setProduto(produtoCarrinho.getProduto() != null && produtoCarrinho.getProduto().getId() != null ?
                                this.produtoRepository.findOne(produtoCarrinho.getProduto().getId()): produtoCarrinho.getProduto())
         );
    }

    @Override
    public List<Produto> getProdutosFiltrados(ProdutoFilter produtoFilter) {
        return this.produtoFilterRepository.filterByParameters(produtoFilter);
    }
}
