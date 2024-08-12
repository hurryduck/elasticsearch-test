package com.elastic.stack.product.repository;

import com.elastic.stack.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCreateDate(LocalDate createDate);
}
