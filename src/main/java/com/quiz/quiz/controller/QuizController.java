package com.quiz.quiz.controller;

import com.quiz.quiz.model.QuestionWrapper;
import com.quiz.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ,
                                             @RequestParam String title) {
        String response = quizService.createQuiz(category, numQ, title);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getQuestionById/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        List<QuestionWrapper> questions = quizService.getQuizQuestions(id);
        return ResponseEntity.ok(questions);
    }
}
