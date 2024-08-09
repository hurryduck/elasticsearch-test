package com.elastic.stack.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "testIndex")
public class TestDoc {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "nori")
    private String name;
}
