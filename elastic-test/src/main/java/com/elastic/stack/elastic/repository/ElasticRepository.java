package com.elastic.stack.elastic.repository;

import com.elastic.stack.elastic.entity.ProductGroupDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<ProductGroupDoc, String> {
}
