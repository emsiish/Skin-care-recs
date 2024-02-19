package com.example.skincarerecs.service.impl;

import com.example.skincarerecs.controller.dto.QuestionDto;
import com.example.skincarerecs.mapper.QuestionMapper;
import com.example.skincarerecs.repository.QuestionRepository;
import com.example.skincarerecs.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionDto> getQuestions() {
        log.info("Getting all questions");
        return questionMapper.mapToQuestionDtoList(questionRepository.findAll());
    }
}
