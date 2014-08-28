package QuizEngine;

/**
 * Created by 212391398 on 8/15/14.
 */
public class Question extends QuizElement
{
    Question(String text, int type, int key)
    {
        super(text, type, key);
    }

    boolean tryAnswer(Answer answer)
    {
        boolean result = (answer.type.intValue() == this.type.intValue() && answer.key.intValue() == this.key.intValue());
        return result;
    }
}
