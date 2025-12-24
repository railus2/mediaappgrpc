package org.example.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.mediaclient.dto.VideoDto;
import org.example.mediaclient.mapper.VideoMapper;
import org.springframework.stereotype.Service;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoServiceGrpc;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub stub;
    private final VideoMapper mapper;
    public VideoServiceClient(VideoMapper mapper) {
        this.mapper = mapper;
    }
    public VideoDto uploadVideo(UploadVideoRequest videoRequest) {
        Video video = stub.uploadVideo(videoRequest);
        return mapper.fromVideoProtoToVideoDto(video);
    }
}
