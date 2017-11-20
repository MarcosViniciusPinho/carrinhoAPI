package br.com.carrinho.service.impl;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.repository.CarrinhoRepository;
import br.com.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Carrinho create(Carrinho carrinho) {
//        carrinho.dataCompra.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)   .withLocale(new Locale("pt", "br")));
        carrinho.setDataCompra(LocalDateTime.now());
        return this.carrinhoRepository.save(carrinho);
    }

}
