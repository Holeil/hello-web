package com.start.web.component;

import com.start.web.domain.MessageSearch;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

//Тут могут быть ошибки!
@Repository
@Transactional
public class MessageSearcher {
    @PersistenceContext
    private EntityManager entityManager;

    public Page<MessageSearch> searchMessages(String text, Pageable page) {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(MessageSearch.class).get();

        Query query = queryBuilder
                        .keyword()
                        .onFields("title")
                        .andField("text")
                        .andField("tag")
                        .andField("comment.text")
                        .andField("specialty")
                        .matching(text)
                        .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, MessageSearch.class);
        jpaQuery.setProjection(FullTextQuery.THIS, FullTextQuery.SCORE);

        long total = jpaQuery.getResultSize();
        jpaQuery.setFirstResult((int)page.getOffset()).setMaxResults(page.getPageSize());

        @SuppressWarnings("unchecked")
        List<Object[]> result = jpaQuery.getResultList();
        List<MessageSearch> fine = new LinkedList<>();
        for(Object[] i : result) {
            fine.add((MessageSearch) i[0]);
        }

        return new PageImpl<>(fine, page, total);
    }
} 