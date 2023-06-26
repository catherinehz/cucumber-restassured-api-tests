package com.cucumber.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class ObjectMapperHelper {
    private static ObjectMapper objectMapper;

    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            synchronized (ObjectMapperHelper.class) {
                if (objectMapper == null) {
                    objectMapper = new ObjectMapper();
                    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                    objectMapper.setSerializationInclusion(NON_NULL);
                    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
                }
            }
        }
        return objectMapper;
    }

    public static String writeObjectToJsonString(Object object) throws IOException {
        ObjectMapper mapper = getInstance();
        return mapper.writeValueAsString(object);
    }

    public static <T> T readJsonFileToObject(String filePath, Class<T> valueType) throws IOException {
        ObjectMapper mapper = getInstance();
        return mapper.readValue(new File(filePath), valueType);
    }
}
