package org.example.mediaserver.service;

import org.example.mediaserver.dao.VideoDao;
import org.example.mediaserver.model.CreatorModel;
import org.example.mediaserver.model.VideoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    private final VideoDao videoDao;

    public VideoService(VideoDao videoDao) {
        this.videoDao = videoDao;
    }

    public VideoModel uploadVideo(String title, String description, String url, int durationSeconds, CreatorModel creator) {
        VideoModel video = new VideoModel(
                UUID.randomUUID().toString(),
                title,
                description,
                url,
                durationSeconds,
                creator
        );
        return videoDao.save(video);
    }

    public VideoModel getVideoById(String id) {
        return videoDao.findById(id)
                .orElseGet(() -> new VideoModel(id, "NOT_FOUND", "Video not found", "", 0, null));
    }

    public List<VideoModel> getVideosByCreatorId(String creatorId) {
        return videoDao.findByCreatorId(creatorId);
    }
}
