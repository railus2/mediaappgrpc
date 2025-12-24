package org.example.mediaserver.dao;

import org.example.mediaserver.model.VideoModel;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class VideoDao {

    private final Map<String, VideoModel> videos = new ConcurrentHashMap<>();

    public VideoModel save(VideoModel video) {
        videos.put(video.getId(), video);
        return video;
    }

    public Optional<VideoModel> findById(String id) {
        return Optional.ofNullable(videos.get(id));
    }

    public List<VideoModel> findByCreatorId(String creatorId) {
        List<VideoModel> result = new ArrayList<>();
        for (VideoModel v : videos.values()) {
            if (v.getCreator() != null && creatorId.equals(v.getCreator().getId())) {
                result.add(v);
            }
        }
        return result;
    }
}
