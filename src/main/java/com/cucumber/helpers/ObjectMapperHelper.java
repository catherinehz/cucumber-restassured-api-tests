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
                }
            }
        }
        return objectMapper;
    }

    public static void writeObjectToJsonFile(Object object, String filePath) throws IOException {
        ObjectMapper mapper = getInstance();
        mapper.writeValue(new File(filePath), object);
    }

    public static String writeObjectToJsonString(Object object) throws IOException {
        ObjectMapper mapper = getInstance();
        return mapper.writeValueAsString(object);
    }

    public static <T> T readJsonFileToObject(String filePath, Class<T> valueType) throws IOException {
        ObjectMapper mapper = getInstance();
        return mapper.readValue(new File(filePath), valueType);
    }

    public static <T> T readJsonStringToObject(String jsonString, Class<T> valueType) throws IOException {
        ObjectMapper mapper = getInstance();
        return mapper.readValue(jsonString, valueType);
    }
}
