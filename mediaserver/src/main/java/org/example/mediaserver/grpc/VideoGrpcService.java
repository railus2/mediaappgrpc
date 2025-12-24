package org.example.mediaserver.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.mediaserver.mapper.CreatorMapper;
import org.example.mediaserver.mapper.VideoMapper;
import org.example.mediaserver.model.CreatorModel;
import org.example.mediaserver.model.VideoModel;
import org.example.mediaserver.service.VideoService;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoIdRequest;
import org.xproce.lab.VideoServiceGrpc;

@GrpcService
public class VideoGrpcService extends VideoServiceGrpc.VideoServiceImplBase {

    private final VideoService videoService;
    private final VideoMapper videoMapper;
    private final CreatorMapper creatorMapper;

    public VideoGrpcService(VideoService videoService, VideoMapper videoMapper, CreatorMapper creatorMapper) {
        this.videoService = videoService;
        this.videoMapper = videoMapper;
        this.creatorMapper = creatorMapper;
    }

    @Override
    public void uploadVideo(UploadVideoRequest request, StreamObserver<Video> responseObserver) {
        CreatorModel creator = creatorMapper.fromProto(request.getCreator());

        VideoModel saved = videoService.uploadVideo(
                request.getTitle(),
                request.getDescription(),
                request.getUrl(),
                request.getDurationSeconds(),
                creator
        );

        responseObserver.onNext(videoMapper.toProto(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getVideo(VideoIdRequest request, StreamObserver<Video> responseObserver) {
        VideoModel video = videoService.getVideoById(request.getId());
        responseObserver.onNext(videoMapper.toProto(video));
        responseObserver.onCompleted();
    }
}
