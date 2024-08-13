package com.elastic.stack.elastic.service;

import com.elastic.stack.elastic.entity.ProductGroupDoc;
import com.elastic.stack.elastic.repository.ElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ElasticService {
    private final ElasticRepository repository;

    // Create or Update
    public ProductGroupDoc save(ProductGroupDoc productGroupDoc) {
        return this.repository.save(productGroupDoc);
    }

    // Read all documents
    public List<ProductGroupDoc> findAll() {
        Iterable<ProductGroupDoc> iterable = this.repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    // Delete all documents
    public void deleteAll() {
        this.repository.deleteAll();
    }
}
