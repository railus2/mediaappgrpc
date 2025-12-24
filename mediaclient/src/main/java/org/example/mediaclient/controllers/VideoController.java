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
                .setName("Xproce")
                .setEmail("hirchoua.badr@gmail.com")
                .setId("2")
                .build();

        UploadVideoRequest request = UploadVideoRequest.newBuilder()
                .setTitle("grpc 101")
                .setDescription("The gRPC 101 is an introductory course to master Grpc")
                .setUrl("https://github.com/badrhr/gRPC101")
                .setDurationSeconds(380)
                .setCreator(creator)
                .build();

        return videoService.uploadVideo(request);
    }
}
