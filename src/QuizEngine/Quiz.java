package QuizEngine;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by 212391398 on 8/15/14.
 */
public class Quiz {

    class QuestionSet
    {
        public Question question;
        public boolean result = false;
        public ArrayList<Answer> answerChoices = new ArrayList<Answer>();

        QuestionSet(Question question, boolean result)
        {
            this.question = question;
            this.result = result;
        }

        boolean TryAnswer(Answer answer)
        {
            this.result = question.tryAnswer(answer);
            return this.result;
        }

        void reset()
        {
            this.result = false;
        }
    }

    private ArrayList<QuestionSet> questionSet;
    private int currentQuestion;
    private int numberOfAnswers;


    Quiz()
    {
        questionSet = new ArrayList<QuestionSet>();
        currentQuestion = 0;
        numberOfAnswers = 4;
    }

    public void addQuestion(Question q)
    {
        QuestionSet qs = new QuestionSet(q, false);
        ArrayList<Answer> keys = AnswerBankManager.getInstance().generateAnswerChoices(q.type, q.key, numberOfAnswers) ;
        qs.answerChoices.addAll(keys);
        questionSet.add( qs );
    }

    public int size()
    {
        return questionSet.size();
    }

    public void displayQuestion(int index)
    {
        if (index >= 0 && index < size())
        {
            QuestionSet qs = questionSet.get(index);
            System.out.println(qs.question.text);
            for (int i=0; i<qs.answerChoices.size(); i++)
            {
                System.out.println(String.format("%d) %s", i, qs.answerChoices.get(i).text));
            }
        }
    }

    public boolean tryAnswerCurrent(Answer answer)
    {
        return questionSet.get(currentQuestion++).TryAnswer(answer);
    }

    public boolean tryAnswerCurrent(int index)
    {
        boolean result = false;

        QuestionSet qs = questionSet.get(currentQuestion);

        result = qs.TryAnswer(qs.answerChoices.get(index));

        currentQuestion++;

        return result;
    }

    public void reset()
    {
        currentQuestion = 0;
        for (QuestionSet qs : questionSet )
        {
            qs.reset();
        }
    }

    public void print()
    {
        Iterator<QuestionSet> iterator = questionSet.iterator();

        while (iterator.hasNext())
        {
            QuestionSet qs = iterator.next();
            System.out.println(qs.question.text);
        }
    }

    public void printResults()
    {
        int correct = 0;
        Iterator<QuestionSet> iterator = questionSet.iterator();

        while (iterator.hasNext())
        {
            QuestionSet qs = iterator.next();
            correct = correct + (qs.result ? 1 : 0);
        }

        System.out.format("You answered %d out of %d correctly", correct, questionSet.size());
    }
}

