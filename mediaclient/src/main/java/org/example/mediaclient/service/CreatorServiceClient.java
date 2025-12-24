package org.example.mediaclient.service;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.example.mediaclient.dto.CreatorDto;
import org.example.mediaclient.dto.VideoStreamDto;
import org.example.mediaclient.mapper.CreatorMapper;
import org.example.mediaclient.mapper.VideoStreamMapper;
import org.springframework.stereotype.Service;
import org.xproce.lab.Creator;
import org.xproce.lab.CreatorIdRequest;
import org.xproce.lab.CreatorServiceGrpc;
import org.xproce.lab.VideoStream;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    private CreatorServiceGrpc.CreatorServiceBlockingStub stub;

    private final CreatorMapper creatorMapper;
    private final VideoStreamMapper videoStreamMapper;

    public CreatorServiceClient(CreatorMapper creatorMapper, VideoStreamMapper videoStreamMapper) {
        this.creatorMapper = creatorMapper;
        this.videoStreamMapper = videoStreamMapper;
    }

    public CreatorDto getCreatorById(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder().setId(id).build();
        Creator creator = stub.getCreator(request);
        return creatorMapper.fromCreatorProtoToCreatorDto(creator);
    }

    public VideoStreamDto getCreatorVideos(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder().setId(id).build();
        VideoStream stream = stub.getCreatorVideos(request);
        return videoStreamMapper.fromVideoStreamProtoToDto(stream);
    }
}
