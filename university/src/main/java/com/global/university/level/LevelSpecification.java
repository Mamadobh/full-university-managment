package com.global.book_network.book;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {


    public static Specification<Book> withOwnerId(Integer ownerId) {
        return (root, query, cb) -> {
            return cb.equal(root.get("owner").get("id"), ownerId);
        };
    }
}
