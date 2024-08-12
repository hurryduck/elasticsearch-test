package com.elastic.stack.elastic.entity;

import com.elastic.stack.product.entity.Destination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "productgroup")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGroupDoc {
    @Id
    private Long id;
    private Destination description;
    private int nights;
}
