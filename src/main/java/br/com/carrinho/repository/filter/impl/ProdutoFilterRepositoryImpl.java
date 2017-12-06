package br.com.carrinho.repository.filter.impl;

import br.com.carrinho.entity.Produto;
import br.com.carrinho.repository.filter.ProdutoFilterRepository;
import br.com.carrinho.repository.filter.model.ProdutoFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProdutoFilterRepositoryImpl extends FilterRepositoryImpl<Produto, ProdutoFilter> implements ProdutoFilterRepository {

    @Override
    public Class<Produto> getEntityClass(){
        return Produto.class;
    }

    @Override
    public Predicate[] criarRestricoes(ProdutoFilter produtoFilter, CriteriaBuilder builder, Root<Produto> root){
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(produtoFilter.getNome())) {
            predicates.add(builder.like(
                    builder.lower(root.get("nome")), "%" + produtoFilter.getNome() + "%"));
        }

        if (StringUtils.isNotEmpty(produtoFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get("descricao")), "%" + produtoFilter.getDescricao() + "%"));
        }

        if (produtoFilter.getValor() != null) {
            predicates.add(builder.equal(
                    builder.lower(root.get("valor")), produtoFilter.getValor()));
        }

        if (StringUtils.isNotEmpty(produtoFilter.getNomeCategoria())) {
            predicates.add(builder.like(
                    builder.lower(root.join("categoria").get("nome")), "%" + produtoFilter.getNomeCategoria() + "%"));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
