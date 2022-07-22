package com.example.versioning.service.v3;

import com.example.versioning.model.Document;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceV3Impl implements DocumentServiceV3 {
    @Override
    public Document get(String id) {
        Document doc = new Document();
        doc.setIsbn("ISBN-12345-3");
        doc.setTitle("The Old Man And The Sea (Version 3)");
        return doc;
    }
}
