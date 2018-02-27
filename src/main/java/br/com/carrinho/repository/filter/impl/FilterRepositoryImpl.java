package br.com.carrinho.repository.filter.impl;

import br.com.carrinho.repository.filter.FilterRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class FilterRepositoryImpl<E, F> implements FilterRepository<E, F> {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<E> filterByParameters(F filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(getEntityClass());
        Root<E> root = criteria.from(getEntityClass());

        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nome")));

        TypedQuery<E> query = manager.createQuery(criteria);
        return query.getResultList();
    }

}
