package com.example.skincarerecs.mapper;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.entity.Tag;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TagMapper {
    Tag mapToTag(TagDto tagDto);
    TagDto mapToTagDto(Tag tag);
    List<TagDto> mapToTagDtoList(List<Tag> all);
    List<Tag> mapToTagList(List<TagDto> tags);
}
