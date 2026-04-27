/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.elearning.backend.controller;

import com.elearning.backend.model.Answer;
import com.elearning.backend.model.Question;
import com.elearning.backend.model.Quiz;
import com.elearning.backend.repository.AnswerRepository;
import com.elearning.backend.repository.QuestionRepository;
import com.elearning.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/course/{courseId}")
    public List<Quiz> getQuizzesByCourse(
            @PathVariable Long courseId) {
        List<Quiz> quizzes = quizService
            .getQuizzesByCourse(courseId);
        quizzes.forEach(quiz -> {
            List<Question> questions = 
                questionRepository
                    .findByQuizId(quiz.getId());
            questions.forEach(question -> {
                List<Answer> answers = 
                    answerRepository
                        .findByQuestionId(
                            question.getId());
                question.setAnswers(answers);
            });
            quiz.setQuestions(questions);
        });
        return quizzes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(
            @PathVariable Long id) {
        return quizService.getQuizById(id)
            .map(quiz -> {
                List<Question> questions =
                    questionRepository
                        .findByQuizId(quiz.getId());
                questions.forEach(question -> {
                    List<Answer> answers =
                        answerRepository
                            .findByQuestionId(
                                question.getId());
                    question.setAnswers(answers);
                });
                quiz.setQuestions(questions);
                return ResponseEntity.ok(quiz);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @PostMapping("/{quizId}/questions")
    public Question addQuestion(
            @PathVariable Long quizId,
            @RequestBody Question question) {
        question.setQuiz(
            quizService.getQuizById(quizId)
                .orElseThrow());
        return questionRepository.save(question);
    }

    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer(
            @PathVariable Long questionId,
            @RequestBody Answer answer) {
        answer.setQuestion(
            questionRepository.findById(questionId)
                .orElseThrow());
        return answerRepository.save(answer);
    }
}
