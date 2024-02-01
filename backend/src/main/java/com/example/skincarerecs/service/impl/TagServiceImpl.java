package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.TagDto;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.repository.TagRepository;
import com.example.skincarerecs.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.skincarerecs.mapper.TagMapper.TAG_MAPPER;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    @Override
    public TagDto addTag(TagDto tag) {
        Tag tagEntity = TAG_MAPPER.mapToTag(tag);

        tagRepository.save(tagEntity);

        return TAG_MAPPER.mapToTagResource(tagEntity);
    }

    @Override
    public TagDto getTagById(Long id) {
        return TAG_MAPPER.mapToTagResource(tagRepository.findById(id).orElseThrow());
    }

    @Override
    public TagDto updateTag(Long id, TagDto tag) {
        return null;
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
