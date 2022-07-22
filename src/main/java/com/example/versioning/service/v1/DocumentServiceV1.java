package com.example.versioning.service.v1;

import com.example.versioning.model.Document;

import java.util.List;

public interface DocumentServiceV1 {
    List<Document> getAll();
    Document get(String id);
}
