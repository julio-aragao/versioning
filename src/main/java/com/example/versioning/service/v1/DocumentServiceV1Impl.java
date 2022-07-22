package com.example.versioning.service.v1;

import com.example.versioning.model.Document;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DocumentServiceV1Impl implements DocumentServiceV1 {
    @Override
    public List<Document> getAll() {
        Document doc = new Document();
        doc.setTitle("V1 document");
        doc.setIsbn("V1 ISBN");
        Document doc2 = new Document();
        doc2.setTitle("Another V1 document");
        doc2.setIsbn("Another V1 ISBN");

        return Arrays.asList(doc, doc2);
    }

    @Override
    public Document get(String id) {
        // this should be refactored to be reused
        doSomething1();
        Result result = doSomething2(id);
        Document doc = doSomething3(result);
        //

        doc.setIsbn("ISBN-12345");
        doc.setTitle("The Old Man And The Sea");
        return doc;
    }

    private Document doSomething3(Result result) {
        return new Document();
    }

    private Result doSomething2(String id) {
        return new Result();
    }

    private void doSomething1() {

    }

    class Result {

    }
}
