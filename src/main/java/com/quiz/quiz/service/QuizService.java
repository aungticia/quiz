package com.quiz.quiz.service;

import com.quiz.quiz.model.QuestionWrapper;
import com.quiz.quiz.model.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface QuizService {
    String createQuiz(String category, int numQ, String title);
    List<QuestionWrapper> getQuizQuestions(Integer id);

    ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);
}
