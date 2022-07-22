package com.example.versioning.service.v2;

import com.example.versioning.model.v2.Document;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class DocumentServiceV2Impl implements DocumentServiceV2 {
    @Override
    public List<Document> getAll() {
        Document doc = new Document();
        doc.setName("V2 Document");
        doc.setDateOfLaunch(new Date());

        return Arrays.asList(doc);
    }

    @Override
    public Document get(String id) {
        Document doc = new Document();
        doc.setName("On The Road");

        doc.setDateOfLaunch( Date.from(
                LocalDate.of(1957, 9, 5)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant())
        );

        return doc;
    }
}
