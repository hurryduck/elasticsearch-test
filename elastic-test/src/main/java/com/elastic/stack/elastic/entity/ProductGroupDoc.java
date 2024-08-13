package com.elastic.stack.elastic.entity;

import com.elastic.stack.product.entity.Destination;
import com.elastic.stack.product.entity.ProductInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Map;

@Document(indexName = "product_group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGroupDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private Destination description;
    @Field(type = FieldType.Keyword)
    private int nights;

    @Field(type = FieldType.Object)
    private Map<Long, ProductInformation> productList;
    private int viewCount;
}
