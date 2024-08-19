package com.elastic.stack.product.entity;

import jakarta.persistence.Embeddable;
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
    private Set<String> keywordSet;

    public String getSearchKeyword() {
        return String.join(" ", keywordSet);
    }
}
