package com.cucumber.helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public enum ScenarioContext {

    CONTEXT;

    public static final String RESPONSE = "RESPONSE";

    private Map<String, Object> expectedValueMap = new HashMap<>();

    public void reset() {
        expectedValueMap.clear();
    }

    public void setExpectedValue(String key, Object value) {
        expectedValueMap.put(key, value);
    }

    public Object getExpectedValue(String key) {
        return expectedValueMap.get(key);
    }

    private Map<String, Object> store = new ConcurrentHashMap<>();

    public void putInStore(String name, Object item) {
        store.put(name, item);
    }

    @SuppressWarnings("unchecked")
    public <T> T getFromStore(String name, Class<T> classType) {
        return (T) Optional.ofNullable(store.get(name))
                .filter(v -> classType.isAssignableFrom(v.getClass()))
                .orElse(null);
    }

}
