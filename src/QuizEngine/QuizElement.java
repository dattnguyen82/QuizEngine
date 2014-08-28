package QuizEngine;

/**
 * Created by 212391398 on 8/15/14.
 */
public class QuizElement
{
    protected String text;
    protected Integer type;
    protected Integer key;

    QuizElement()
    {
        this.text = new String();
        this.type = new Integer(-1);
        this.key = new Integer(-1);
    }

    QuizElement(String text, int type, int key)
    {
        this.text = new String(text);
        this.type = new Integer(type);
        this.key = new Integer(key);
    }

}
