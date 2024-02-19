package com.example.skincarerecs.service;

import com.example.skincarerecs.controller.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getQuestions();
}
