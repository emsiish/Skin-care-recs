package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TagMapper {
    TagMapper TAG_MAPPER = Mappers.getMapper(TagMapper.class);

    Tag mapToTag(TagDto tagDto);
    TagDto mapToTagResource(Tag tag);
    List<TagDto> mapToTagResourceList(List<Tag> all);
    List<Tag> mapToTagList(List<TagDto> tags);
}
