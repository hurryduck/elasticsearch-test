package com.elastic.stack.elastic.repository;

import com.elastic.stack.elastic.entity.TestDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticRepository extends ElasticsearchRepository<TestDoc, String> {
    List<TestDoc> findByName(String name);
}
