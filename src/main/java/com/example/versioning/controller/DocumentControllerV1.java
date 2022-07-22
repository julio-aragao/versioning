package com.example.versioning.controller;

import com.example.versioning.custom.VersionGetMapping;
import com.example.versioning.custom.VersionMapping;
import com.example.versioning.model.Document;
import com.example.versioning.service.v1.DocumentServiceV1;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@VersionMapping(version = "1", value = "/documents")
//@RequestMapping(value = "/documents")
public class DocumentControllerV1 {

    private final DocumentServiceV1 service;

    public DocumentControllerV1(DocumentServiceV1 service) {
        this.service = service;
    }

    @VersionGetMapping("/all")
    public ResponseEntity<List<Document>> getDocuments() {
        return ResponseEntity.ok(service.getAll());
    }

    @VersionGetMapping("/{id}")
    public ResponseEntity<Document> getDocument(String id) {
        return ResponseEntity.ok(service.get(id));
    }
}
