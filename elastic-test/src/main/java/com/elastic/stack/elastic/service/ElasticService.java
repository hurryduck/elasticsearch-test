package com.elastic.stack.elastic.service;

import com.elastic.stack.elastic.entity.TestDoc;
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
    public TestDoc save(TestDoc testDoc) {
        return this.repository.save(testDoc);
    }

    // Read all documents
    public List<TestDoc> findAll() {
        Iterable<TestDoc> iterable = this.repository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    // Read a document by ID
    public Optional<TestDoc> findById(String id) {
        return this.repository.findById(id);
    }

    // Read documents by name (custom query)
    public List<TestDoc> findByName(String name) {
        return this.repository.findByName(name);
    }

    // Update a document by ID (save method is used for both create and update)
    @Transactional
    public TestDoc update(String id, TestDoc newDoc) {
        return repository.findById(id)
                .map(existingDoc -> {
                    existingDoc.setName(newDoc.getName()); // Update fields
                    return repository.save(existingDoc);
                })
                .orElseThrow(() -> new RuntimeException("Document not found with id " + id));
    }

    // Delete a document by ID
    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    // Delete all documents
    public void deleteAll() {
        this.repository.deleteAll();
    }

    // Delete documents by name (custom query)
    @Transactional
    public void deleteByName(String name) {
        List<TestDoc> docsToDelete = this.repository.findByName(name);
        this.repository.deleteAll(docsToDelete);
    }
}
