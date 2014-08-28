package QuizEngine;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 212391398 on 8/15/14.
 */
public class QuizFactory {

    QuizFactory(){ }

    public Quiz generateQuiz(int numberOfQuestions)
    {
        Quiz quiz = new Quiz();

        ArrayList<Question> questions = QuestionBankManager.getInstance().generateQuestionSet(numberOfQuestions);

        Iterator<Question> iterator = questions.iterator();

        while(iterator.hasNext())
        {
            quiz.addQuestion((Question)iterator.next());
        }

        return quiz;
    }

}
