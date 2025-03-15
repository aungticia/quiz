package com.quiz.quiz.controller;

import com.quiz.quiz.model.Question;
import com.quiz.quiz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // Get all the questions
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> response = questionService.getAllQuestions();
        return ResponseEntity.ok(response);
    }

    // Get the question by category
    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable String category) {
        List<Question> categories = questionService.getQuestionsByCategory(category);
        if (categories == null || categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No questions found for category: " + category);
        }
        return ResponseEntity.ok(categories);
    }

    // Add your question
    @PostMapping("/add")
    public String addQuestions(@RequestBody Question question) {
        return questionService.addQuestions(question);
    }
}
