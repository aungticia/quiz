package com.quiz.quiz.service;

import com.quiz.quiz.model.Question;
import com.quiz.quiz.model.QuestionWrapper;
import com.quiz.quiz.model.Quiz;
import com.quiz.quiz.model.Response;
import com.quiz.quiz.repository.QuestionRepo;
import com.quiz.quiz.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService{
    private final QuizRepo quizRepo;
    private final QuestionRepo questionRepo;

    @Autowired
    public QuizServiceImpl(QuizRepo quizRepo, QuestionRepo questionRepo) {
        this.quizRepo = quizRepo;
        this.questionRepo = questionRepo;
    }

    public String createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findRandomQuestionByCategory(category, numQ);
        if (questions.isEmpty()) {
            return "Failed: No questions available for this category.";
        }
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return "Quiz created successfully!";
    }

    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isEmpty()) {
            return new ArrayList<>();
        }
        List<Question> questionsFromDb = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDb) {
            questionsForUser.add(new QuestionWrapper(q.getId(), q.getQuestionTitle(),
                    q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4()));
        }
        return questionsForUser;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getCorrectAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
