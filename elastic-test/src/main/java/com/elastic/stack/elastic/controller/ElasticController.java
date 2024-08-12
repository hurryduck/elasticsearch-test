package com.elastic.stack.elastic.controller;

import com.elastic.stack.elastic.entity.TestDoc;
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
    public TestDoc createTestDoc(@RequestBody TestDoc testDoc) {
        log.info("POST /elastic - Creating/Updating a document");
        return this.elasticService.save(testDoc);
    }

    // Get all documents
    @GetMapping
    public List<TestDoc> getAllTestDocs() {
        log.info("GET /elastic - Fetching all documents");
        return this.elasticService.findAll();
    }

    // Get a document by ID
    @GetMapping("/{id}")
    public Optional<TestDoc> getTestDocById(@PathVariable String id) {
        log.info("GET /elastic/{} - Fetching document by ID", id);
        return this.elasticService.findById(id);
    }

    // Get documents by name
    @GetMapping("/search")
    public List<TestDoc> getTestDocsByName(@RequestParam String name) {
        log.info("GET /elastic/search?name={} - Fetching documents by name", name);
        return this.elasticService.findByName(name);
    }

    // Update a document by ID
    @PutMapping("/{id}")
    public TestDoc updateTestDoc(@PathVariable String id, @RequestBody TestDoc testDoc) {
        log.info("PUT /elastic/{} - Updating document with ID", id);
        return this.elasticService.update(id, testDoc);
    }

    // Delete a document by ID
    @DeleteMapping("/{id}")
    public void deleteTestDocById(@PathVariable String id) {
        log.info("DELETE /elastic/{} - Deleting document with ID", id);
        this.elasticService.deleteById(id);
    }

    // Delete all documents
    @DeleteMapping
    public void deleteAllTestDocs() {
        log.info("DELETE /elastic - Deleting all documents");
        this.elasticService.deleteAll();
    }

    // Delete documents by name
    @DeleteMapping("/search")
    public void deleteTestDocsByName(@RequestParam String name) {
        log.info("DELETE /elastic/search?name={} - Deleting documents by name", name);
        this.elasticService.deleteByName(name);
    }
}
