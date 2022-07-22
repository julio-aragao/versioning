package com.example.versioning.controller;

import com.example.versioning.custom.VersionGetMapping;
import com.example.versioning.custom.VersionMapping;
import com.example.versioning.model.v2.Document;
import com.example.versioning.service.v2.DocumentServiceV2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@VersionMapping(version = "2", value = "/documents", headers = {"X-API-VERSION=2"})
//@RequestMapping(value = "/documents")
public class DocumentControllerV2 {

    private final DocumentServiceV2 service;

    public DocumentControllerV2(DocumentServiceV2 service) {
        this.service = service;
    }

    @VersionGetMapping("/all")
    public ResponseEntity<List<Document>> getDocuments() {
        return ResponseEntity.ok(service.getAll());
    }

    @VersionGetMapping(value = "/{id}")
    public ResponseEntity<Document> getDocument(String id) {
        return ResponseEntity.ok(service.get(id));
    }
}
