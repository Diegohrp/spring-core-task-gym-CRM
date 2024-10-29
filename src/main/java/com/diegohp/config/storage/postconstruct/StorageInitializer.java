package com.diegohp.config.storage.postconstruct;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public abstract class StorageInitializer<K, T> {
    private final Resource dataFile;
    private final Map<K, T> storage;
    private final Class<T> type;

    public StorageInitializer(Resource dataFile, Map<K, T> storage, Class<T> type) {
        this.dataFile = dataFile;
        this.storage = storage;
        this.type = type;
    }

    protected abstract K getId(T entity);

   /* @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(dataFile.getInputStream()))) {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, type);
            List<T> entities = mapper.readValue(reader, javaType);
            for (T entity : entities) {
                K id = getId(entity);
                storage.put(id, entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    */
}
