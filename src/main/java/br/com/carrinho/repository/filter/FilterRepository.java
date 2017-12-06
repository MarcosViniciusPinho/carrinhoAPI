package br.com.carrinho.repository.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public interface FilterRepository<E, F> {

    List<E> filterByParameters(F filter);

    Class<E> getEntityClass();

    Predicate[] criarRestricoes(F filter, CriteriaBuilder builder, Root<E> root);

}
