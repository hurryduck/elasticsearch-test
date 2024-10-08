package com.elastic.stack.elastic.controller;

import com.elastic.stack.elastic.entity.ProductGroupDoc;
import com.elastic.stack.elastic.service.ElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/elastic")
@RequiredArgsConstructor
public class ElasticController {
    private final ElasticService elasticService;

    // Create or Update a document
    @PostMapping
    public ProductGroupDoc createTestDoc(@RequestBody ProductGroupDoc productGroupDoc) {
        log.info("POST /elastic - Creating/Updating a document");
        return this.elasticService.save(productGroupDoc);
    }

    // Get all documents
    @GetMapping
    public List<ProductGroupDoc> getAllTestDocs() {
        log.info("GET /elastic - Fetching all documents");
        return this.elasticService.findAll();
    }

    // Delete all documents
    @DeleteMapping
    public void deleteAllTestDocs() {
        log.info("DELETE /elastic - Deleting all documents");
        this.elasticService.deleteAll();
    }
}
