package org.example.mediaserver.mapper;

import org.example.mediaserver.model.CreatorModel;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorMapper {

    public Creator toProto(CreatorModel model) {
        if (model == null) return Creator.getDefaultInstance();
        return Creator.newBuilder()
                .setId(model.getId() == null ? "" : model.getId())
                .setName(model.getName() == null ? "" : model.getName())
                .setEmail(model.getEmail() == null ? "" : model.getEmail())
                .build();
    }

    public CreatorModel fromProto(Creator proto) {
        if (proto == null) return null;
        return new CreatorModel(
                proto.getId(),
                proto.getName(),
                proto.getEmail()
        );
    }
}
