package com.elastic.stack.controller;

import com.elastic.stack.entity.TestDoc;
import com.elastic.stack.service.ElasticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/elastic")
public class ElasticController {
    private final ElasticService elasticService;

    @Autowired
    public ElasticController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }

    @PostMapping
    public TestDoc createTestDoc(@RequestBody TestDoc testDoc) {
        return this.elasticService.save(testDoc);
    }

    @GetMapping
    public List<TestDoc> getAllTestDocs() {
        return this.elasticService.findAll();
    }

    @GetMapping("/search")
    public List<TestDoc> getTestDocsByName(@RequestParam String name) {
        return this.elasticService.findByName(name);
    }
}
