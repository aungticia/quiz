package com.quiz.quiz.service;

import com.quiz.quiz.model.Question;
import com.quiz.quiz.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepo questionRepo;

    // Get all the questions
    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    // Get the question by category
    public List<Question> getQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String addQuestions(Question question) {
        questionRepo.save(question);
        return "Success!";
    }
}
