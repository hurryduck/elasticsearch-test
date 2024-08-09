package com.elastic.stack.service;

import com.elastic.stack.entity.TestDoc;
import com.elastic.stack.repository.ElasticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticService {
    private final ElasticRepository repository;

    @Autowired
    public ElasticService(ElasticRepository repository) {
        this.repository = repository;
    }

    public TestDoc save(TestDoc testDoc) {
        return this.repository.save(testDoc);
    }

    public List<TestDoc> findAll() {
        return (List<TestDoc>) this.repository.findAll();
    }

    public List<TestDoc> findByName(String name) {
        return this.repository.findByName(name);
    }
}
