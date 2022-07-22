package com.example.versioning.model.v2;

import lombok.Data;

import java.util.Date;

@Data
public class Document {
    private String name;
    private Date dateOfLaunch;
}
