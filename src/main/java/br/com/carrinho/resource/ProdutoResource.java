package br.com.carrinho.resource;

import br.com.carrinho.entity.Produto;
import br.com.carrinho.repository.filter.model.ProdutoFilter;
import br.com.carrinho.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<List<Produto>> filtrarRegistro(ProdutoFilter produtoFilter){
        return ResponseEntity.ok().body(this.produtoService.getProdutosFiltrados(produtoFilter));
    }

}
