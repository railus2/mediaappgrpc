package org.example.mediaserver.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.example.mediaserver.model.CreatorModel;
import org.example.mediaserver.model.VideoModel;
import org.example.mediaserver.service.CreatorService;
import org.example.mediaserver.service.VideoService;
import org.xproce.lab.Creator;
import org.xproce.lab.CreatorIdRequest;
import org.xproce.lab.CreatorServiceGrpc;
import org.xproce.lab.VideoStream;

import java.util.List;

@GrpcService
public class CreatorGrpcService extends CreatorServiceGrpc.CreatorServiceImplBase {

    private final CreatorService creatorService;
    private final VideoService videoService;
    private final CreatorMapper creatorMapper;
    private final VideoMapper videoMapper;

    public CreatorGrpcService(CreatorService creatorService, VideoService videoService,
                              CreatorMapper creatorMapper, VideoMapper videoMapper) {
        this.creatorService = creatorService;
        this.videoService = videoService;
        this.creatorMapper = creatorMapper;
        this.videoMapper = videoMapper;
    }

    @Override
    public void getCreator(CreatorIdRequest request, StreamObserver<Creator> responseObserver) {
        CreatorModel creator = creatorService.getCreatorById(request.getId());
        responseObserver.onNext(creatorMapper.toProto(creator));
        responseObserver.onCompleted();
    }

    @Override
    public void getCreatorVideos(CreatorIdRequest request, StreamObserver<VideoStream> responseObserver) {
        List<VideoModel> videos = videoService.getVideosByCreatorId(request.getId());

        VideoStream.Builder builder = VideoStream.newBuilder();
        for (VideoModel v : videos) {
            builder.addVideos(videoMapper.toProto(v));
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
