package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.resources.TagResource;
import com.example.skincarerecs.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface TagMapper {
    TagMapper TAG_MAPPER = Mappers.getMapper(TagMapper.class);

    Tag mapToTag(TagResource tagResource);
    TagResource mapToTagResource(Tag tag);
}
