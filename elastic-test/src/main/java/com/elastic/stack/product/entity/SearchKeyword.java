package com.elastic.stack.product.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Embeddable
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class SearchKeyword {
    private String keyword;
    @CreationTimestamp
    private LocalDate createDate;
}
