package com.example.skincarerecs.controller;

import com.example.skincarerecs.controller.dto.QuestionDto;
import com.example.skincarerecs.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<QuestionDto> getQuestions() {
        return questionService.getQuestions();
    }
}
