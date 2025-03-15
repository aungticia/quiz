package com.quiz.quiz.service;


import com.quiz.quiz.model.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
    List<Question> getQuestionsByCategory(String category);

    String addQuestions(Question question);
}
