package com.example.versioning.service.v2;


import com.example.versioning.model.v2.Document;

import java.util.List;

public interface DocumentServiceV2 {
    List<Document> getAll();
    Document get(String id);
}
