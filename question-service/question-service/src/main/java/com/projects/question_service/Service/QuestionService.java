package com.projects.question_service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projects.question_service.Dao.QuestionDao;
import com.projects.question_service.Model.Question;
import com.projects.question_service.Model.QuestionWrapper;
import com.projects.question_service.Model.Response;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();

    }

    public List<Question> getQuestionsByCategory(String category) {

        return questionDao.findAllByCategory(category);   }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Question added successfully";
    }

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "Question deleted successfully"; 
    }

    public ResponseEntity<List<Integer>> generateQuestionsforQuiz(String categoryName, Integer num) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, num);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsbyId(List<Integer> qids) {

        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> question = new ArrayList<>();
        for (Integer id : qids) {
            question.add(questionDao.findById(id).get());
        }

        for(Question q : question) {
            QuestionWrapper wrapper = new QuestionWrapper();

            wrapper.setId(q.getId());
            wrapper.setQuestion(q.getQuestion());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());
            wrappers.add(wrapper);
        }
        

        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score = 0;
        for (Response r : responses) {
            Question question = questionDao.findById(r.getId()).get();
            if (r.getResponse().equals(question.getRightanswer())) {
                score++;
            }
        }
        return new ResponseEntity<>(score, HttpStatus.OK);
    }

}
