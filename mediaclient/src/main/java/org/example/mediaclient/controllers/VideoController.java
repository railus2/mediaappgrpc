package org.example.mediaclient.controllers;

import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.service.VideoServiceClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;

@RestController
public class VideoController {
    private final VideoServiceClient videoService;
    public VideoController(VideoServiceClient videoService) {
        this.videoService = videoService;
    }
    @PostMapping("addVideo")
    public VideoDto uploadVideo() {

        Creator creator = Creator.newBuilder()
                .setName("Bobb")
                .setEmail("Bob@gmail.com")
                .setId("2")
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc 101")
                .setDescription("The gRPC 101 is an introductory course to master Grpc")
                .setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        return videoService.uploadVideo(request);
    }
}
