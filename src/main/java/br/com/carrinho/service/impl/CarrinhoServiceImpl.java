package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.repository.CarrinhoRepository;
import br.com.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Carrinho create(Carrinho carrinho) {
        return this.carrinhoRepository.save(carrinho);
    }

}
