package org.example.mediaserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoModel {
    private String id;
    private String title;
    private String description;
    private String url;
    private int durationSeconds;
    private CreatorModel creator;
}
