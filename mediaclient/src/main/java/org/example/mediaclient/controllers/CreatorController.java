package org.example.mediaclient.controllers;

import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.service.CreatorServiceClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creators")
public class CreatorController {

    private final CreatorServiceClient creatorService;

    public CreatorController(CreatorServiceClient creatorService) {
        this.creatorService = creatorService;
    }

    @GetMapping("/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorService.getCreatorById(id);
    }

    @GetMapping("/{id}/videos")
    public VideoStreamDto getCreatorVideos(@PathVariable String id) {
        return creatorService.getCreatorVideos(id);
    }
}
