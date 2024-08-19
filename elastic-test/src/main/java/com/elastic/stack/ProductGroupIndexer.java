package com.elastic.stack;

import com.elastic.stack.elastic.entity.ProductGroupDoc;
import com.elastic.stack.elastic.repository.ElasticRepository;
import com.elastic.stack.product.entity.*;
import com.elastic.stack.product.repository.ProductGroupRepository;
import com.elastic.stack.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductGroupIndexer {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    private final ElasticRepository elasticRepository;

    private final LocalDate currentDate = LocalDate.now();

    @PostConstruct
    public void productGroupIndexer() {
        storeInDatabase(); // product group DB에 저장
        indexInElasticsearch(); // product group 인덱싱
    }

    private void storeInDatabase() {
        // 패키지 여행 상품 목록 조회
        List<Product> products = this.productRepository.findByCreateDate(this.currentDate);

        // 패키지 여행 상품 그룹핑
        Map<Map.Entry<Destination, Integer>, List<Product>> productByDestinationAndNights = products.stream()
                .collect((Collectors.groupingBy(product ->
                        new AbstractMap.SimpleEntry<>(
                                product.getProductInformation().getDestination(),
                                product.getProductInformation().getNights()
                        )
                )));

        // 패키지 여행 상품 그룹 생성
        productByDestinationAndNights.forEach((key, productList) -> {
            Destination destination = key.getKey();
            Integer nights = key.getValue();
            Map<Long, ProductInformation> productInformationMap = productList.stream()
                    .collect((Collectors.toMap(Product::getId, Product::getProductInformation)));
            Set<String> keywordSet = new HashSet<>();
            keywordSet.add(destination.getKorName());
            keywordSet.addAll(getRandomThemesKeywordSet());

            // 패키지 여행 상품 그룹 값 대입
            ProductGroup productGroup = ProductGroup.builder()
                    .destination(destination)
                    .nights(nights)
                    .createDate(this.currentDate)
                    .searchKeywords(SearchKeyword.builder()
                            .keywordSet(keywordSet)
                            .build())
                    .productList(productInformationMap)
                    .build();

            // 패키지 여행 상품 그룹 저장
            this.productGroupRepository.save(productGroup);
        });
    }

    private void indexInElasticsearch() {
        // DB에 저장된 모든 productGroup 가져오기
        List<ProductGroup> productGroups = this.productGroupRepository.findByCreateDate(this.currentDate);

        // 기존 인덱싱 삭제
        this.elasticRepository.deleteAll();

        // Elasticsearch에 인덱싱
        productGroups.forEach(productGroup -> {
            ProductGroupDoc productGroupDoc = ProductGroupDoc.builder()
                    .id(productGroup.getId())
                    .searchKeywords(productGroup.getSearchKeywords().getSearchKeyword())
                    .destination(productGroup.getDestination())
                    .nights(productGroup.getNights())
                    .productList(productGroup.getProductList())
                    .viewCount(productGroup.getViewCount())
                    .build();

            this.elasticRepository.save(productGroupDoc);
        });
    }

    private Set<String> getRandomThemesKeywordSet() {
        Set<String> keywordSet = new HashSet<>();
        Random random = new Random();

        TravelTheme[] allThemes = TravelTheme.values();

        while (keywordSet.size() < 2) {
            keywordSet.add(allThemes[random.nextInt(allThemes.length)].getDisplayKorName());
        }

        return keywordSet;
    }
}
