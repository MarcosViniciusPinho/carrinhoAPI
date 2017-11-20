package br.com.carrinho.resource;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoResource {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Carrinho> create(@Valid @RequestBody Carrinho carrinho){
        return new ResponseEntity<>(this.carrinhoService.create(carrinho), HttpStatus.CREATED);
    }

}
