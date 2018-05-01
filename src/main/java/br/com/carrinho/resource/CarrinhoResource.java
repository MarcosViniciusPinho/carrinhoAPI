package br.com.carrinho.resource;

import br.com.carrinho.entity.Carrinho;
import br.com.carrinho.facade.CarrinhoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoResource {

    @Autowired
    private CarrinhoFacade carrinhoFacade;

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseEntity<Carrinho> create(@Valid @RequestBody Carrinho carrinho){
        Carrinho carrinhoSalvo = this.carrinhoFacade.create(carrinho);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(carrinhoSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(carrinhoSalvo);
    }

}
