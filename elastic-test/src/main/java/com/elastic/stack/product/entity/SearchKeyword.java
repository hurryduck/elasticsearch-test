package com.elastic.stack.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Set;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchKeyword {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "tbl_product_group_keyword",
            joinColumns = @JoinColumn(name = "product_group_id")
    )
    private Set<String> keywordSet;

    public String getSearchKeyword() {
        return String.join(" ", keywordSet);
    }
}
