package com.example.versioning.controller;

import com.example.versioning.custom.VersionGetMapping;
import com.example.versioning.custom.VersionMapping;
import com.example.versioning.model.Document;
import com.example.versioning.service.v3.DocumentServiceV3;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@VersionMapping(version = "3", value = "/documents", headers = {"X-API-VERSION=3"})
//@RequestMapping(value = "/documents")
public class DocumentControllerV3 {
    private final DocumentServiceV3 service;

    public DocumentControllerV3(DocumentServiceV3 service) {
        this.service = service;
    }

    @VersionGetMapping(value = "/{id}")
    public ResponseEntity<Document> getDocument(String id) {
        return ResponseEntity.ok(service.get(id));
    }
}
