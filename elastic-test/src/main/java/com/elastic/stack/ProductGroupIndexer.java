package com.elastic.stack;

import com.elastic.stack.product.entity.Destination;
import com.elastic.stack.product.entity.Product;
import com.elastic.stack.product.entity.ProductGroup;
import com.elastic.stack.product.entity.ProductInformation;
import com.elastic.stack.product.repository.ProductGroupRepository;
import com.elastic.stack.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductGroupIndexer {
    private final ProductRepository productRepository;
    private final ProductGroupRepository productGroupRepository;

    @PostConstruct
    public void productGroupIndexer() {
        storeInDatabase();
    }

    private void storeInDatabase() {
        System.out.println("\n\n\n");
        System.out.println("store in database start!!!");
        // 패키지 여행 상품 목록 조회
        List<Product> products = this.productRepository.findAll();

        System.out.println("패키지 여행 상품 리스트");
        products.forEach(System.out::println);

        // 패키지 여행 상품 그룹핑
        Map<Map.Entry<Destination, Integer>, List<Product>> productByDestinationAndNights = products.stream()
                .collect((Collectors.groupingBy(product ->
                        new AbstractMap.SimpleEntry<>(
                                product.getProductInformation().getDestination(),
                                product.getProductInformation().getNights()
                        )
                )));

        System.out.println("패키지 여행 상품 그룹핑 리스트");
        productByDestinationAndNights.forEach((key, productList) -> {
            Destination destination = key.getKey();
            Integer nights = key.getValue();
            System.out.println("여행지: " + destination.getKrName());
            System.out.println("숙박일: " + nights + "박" + (nights + 1) + "일");

            for (Product product : productList) {
                System.out.println("  - Product ID: " + product.getId());
                System.out.println("    Title: " + product.getProductInformation().getTitle());
                System.out.println("    Price: " + product.getProductInformation().getPrice());
                System.out.println("    Start Date: " + product.getProductInformation().getStart_date());
                System.out.println("    Thumbnail URL: " + product.getProductInformation().getThumbnailUrl());
                System.out.println("    Travel Agency: " + product.getProductInformation().getTravelAgency());
                System.out.println("    View Count: " + product.getViewCount());
                System.out.println("    Visible: " + product.isVisible());
            }
            System.out.println();
        });

        // 패키지 여행 상품 그룹 생성
        productByDestinationAndNights.forEach((key, productList) -> {
            Destination destination = key.getKey();
            Integer nights = key.getValue();
            Map<Long, ProductInformation> productInformationMap = productList.stream()
                    .collect((Collectors.toMap(Product::getId, Product::getProductInformation)));

            // 패키지 여행 상품 그룹 값 대입
            ProductGroup productGroup = ProductGroup.builder()
                    .description(destination)
                    .nights(nights)
                    .productList(productInformationMap)
                    .build();

            // 패키지 여행 상품 그룹 저장
            this.productGroupRepository.save(productGroup);
        });

        System.out.println("패키지 여행 상품 그룹 목록");
        this.productGroupRepository.findAll().forEach(System.out::println);

        System.out.println("store in database end!!!");
        System.out.println("\n\n\n");
    }
}
