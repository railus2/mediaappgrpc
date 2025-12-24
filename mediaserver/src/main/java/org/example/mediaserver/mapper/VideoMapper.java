package org.example.mediaserver.mapper;

import org.example.mediaserver.model.VideoModel;
import org.springframework.stereotype.Component;
import org.xproce.lab.Video;

@Component
public class VideoMapper {

    private final CreatorMapper creatorMapper;

    public VideoMapper(CreatorMapper creatorMapper) {
        this.creatorMapper = creatorMapper;
    }

    public Video toProto(VideoModel model) {
        if (model == null) return Video.getDefaultInstance();
        return Video.newBuilder()
                .setId(model.getId() == null ? "" : model.getId())
                .setTitle(model.getTitle() == null ? "" : model.getTitle())
                .setDescription(model.getDescription() == null ? "" : model.getDescription())
                .setUrl(model.getUrl() == null ? "" : model.getUrl())
                .setDurationSeconds(model.getDurationSeconds())
                .setCreator(creatorMapper.toProto(model.getCreator()))
                .build();
    }

    public VideoModel fromProto(Video proto) {
        if (proto == null) return null;
        return new VideoModel(
                proto.getId(),
                proto.getTitle(),
                proto.getDescription(),
                proto.getUrl(),
                proto.getDurationSeconds(),
                creatorMapper.fromProto(proto.getCreator())
        );
    }
}
