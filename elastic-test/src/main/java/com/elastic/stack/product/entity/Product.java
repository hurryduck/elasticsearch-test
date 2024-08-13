package com.elastic.stack.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "tbl_product")
@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_site", length = 500)
    private String sourceSite; // 출처 페이지
    @Embedded
    private ProductInformation productInformation;

    private LocalDate createDate;
    private int viewCount;  // 조회수
    private boolean isVisible; // 페이지 노출 가능 여부
}