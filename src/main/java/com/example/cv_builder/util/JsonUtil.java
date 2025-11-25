package com.example.cv_builder.util;

import com.example.cv_builder.model.CV;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static void exportToFile(List<CV> cvs, File file) throws IOException {
        mapper.writeValue(file, cvs);
    }

    public static CV[] importFromFile(File file) throws IOException {
        return mapper.readValue(file, CV[].class);
    }
}
