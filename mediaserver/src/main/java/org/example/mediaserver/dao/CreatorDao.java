package org.example.mediaserver.dao;

import org.example.mediaserver.model.CreatorModel;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CreatorDao {

    private final Map<String, CreatorModel> creators = new ConcurrentHashMap<>();

    public CreatorDao() {
        // Données initiales (pour tester)
        creators.put("2", new CreatorModel("2", "Xproce", "hirchoua.badr@gmail.com"));
        creators.put("1", new CreatorModel("1", "Alice", "alice@example.com"));
    }

    public Optional<CreatorModel> findById(String id) {
        return Optional.ofNullable(creators.get(id));
    }

    public CreatorModel save(CreatorModel creator) {
        creators.put(creator.getId(), creator);
        return creator;
    }
}
