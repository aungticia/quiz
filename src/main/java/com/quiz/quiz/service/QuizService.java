package com.quiz.quiz.service;

import com.quiz.quiz.model.QuestionWrapper;

import java.util.List;

public interface QuizService {
    String createQuiz(String category, int numQ, String title);
    List<QuestionWrapper> getQuizQuestions(Integer id);
}
