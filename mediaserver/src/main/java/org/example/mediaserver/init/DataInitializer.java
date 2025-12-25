package org.example.mediaserver.init;

import org.example.mediaserver.dao.CreatorDao;
import org.example.mediaserver.model.CreatorModel;
import org.example.mediaserver.model.VideoModel;
import org.example.mediaserver.service.VideoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final VideoService videoService;
    private final CreatorDao creatorDao;

    public DataInitializer(VideoService videoService, CreatorDao creatorDao) {
        this.videoService = videoService;
        this.creatorDao = creatorDao;
    }

    @Override
    public void run(String... args) {

        //Récupérer un creator existant
        CreatorModel creator = creatorDao.findById("2")
                .orElseThrow(() -> new RuntimeException("Creator not found"));

        //Ajouter une vidéo
        VideoModel video = videoService.uploadVideo(
                "gRPC 101",
                "The gRPC 101 is an introductory course to master gRPC",
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                380,
                creator
        );

        System.out.println("✅ Vidéo initialisée : " + video.getId());
    }
}
