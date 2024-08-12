package com.elastic.stack.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "testindex")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestDoc {
    @Id
    private String id;
    private String name;
}
