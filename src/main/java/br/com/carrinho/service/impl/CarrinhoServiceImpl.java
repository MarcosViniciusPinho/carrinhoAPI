package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.entity.ProdutoCarrinho;
import br.com.carrinho.repository.CarrinhoRepository;
import br.com.carrinho.service.CarrinhoService;
import br.com.carrinho.service.exception.NullParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Carrinho create(Carrinho carrinho) {
        this.validate(carrinho.getProdutoCarrinhoList());
        return this.carrinhoRepository.save(carrinho);
    }

    private void validate(List<ProdutoCarrinho> produtoCarrinhoList) {
        for (ProdutoCarrinho produtoCarrinho : produtoCarrinhoList) {
            if(produtoCarrinho.getProduto() == null || produtoCarrinho.getProduto().getId() == null){
                throw new NullParameterException("Id de produto ou o própio objeto Produto está nulo na lista de produtoCarrinhoList");
            }
        }
    }

}
