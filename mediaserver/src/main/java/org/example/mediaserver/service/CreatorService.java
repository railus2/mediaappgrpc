package org.example.mediaserver.service;

import org.example.mediaserver.dao.CreatorDao;
import org.example.mediaserver.model.CreatorModel;
import org.springframework.stereotype.Service;

@Service
public class CreatorService {

    private final CreatorDao creatorDao;

    public CreatorService(CreatorDao creatorDao) {
        this.creatorDao = creatorDao;
    }

    public CreatorModel getCreatorById(String id) {
        return creatorDao.findById(id)
                .orElseGet(() -> new CreatorModel(id, "Creator " + id, "creator" + id + "@example.com"));
    }
}
