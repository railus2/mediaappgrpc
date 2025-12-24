package org.example.mediaclient.mapper;

import org.example.mediaclient.dto.VideoStreamDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.VideoStream;

@Component
public class VideoStreamMapper {

    private final ModelMapper mapper;

    public VideoStreamMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public VideoStreamDto fromVideoStreamProtoToDto(VideoStream stream) {
        return mapper.map(stream, VideoStreamDto.class);
    }
}
