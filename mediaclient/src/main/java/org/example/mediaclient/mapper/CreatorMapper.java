package org.example.mediaclient.mapper;

import org.example.mediaclient.dto.CreatorDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorMapper {

    private final ModelMapper mapper;

    public CreatorMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CreatorDto fromCreatorProtoToCreatorDto(Creator creator) {
        return mapper.map(creator, CreatorDto.class);
    }
}
