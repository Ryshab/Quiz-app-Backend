package com.projects.quiz_service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projects.quiz_service.Dao.QuizDao;
import com.projects.quiz_service.Model.QuestionWrapper;
import com.projects.quiz_service.Model.Quiz;
import com.projects.quiz_service.Model.Response;
import com.projects.quiz_service.feign.QuizInterface;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.generateQuestionsforQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionId(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success" , HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> qids = quiz.getQuestionId();
        
        List<QuestionWrapper> questionforUser = quizInterface.getQuestionsbyId(qids).getBody();


        return new ResponseEntity<>(questionforUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

}
