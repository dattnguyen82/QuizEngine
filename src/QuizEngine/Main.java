package QuizEngine;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static String readLine(String format, Object... args) throws IOException {
        if (System.console() != null) {
            return System.console().readLine(format, args);
        }
        System.out.print(String.format(format, args));
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        return reader.readLine();
    }

    public static void main(String[] args) {
	    // write your code here

        AnswerBankManager.getInstance().LoadFromJsonFile("/Users/212391398/source/sandbox/QuizEngine/data/answers-people.json", "people");
        QuestionBankManager.getInstance().LoadFromJsonFile("/Users/212391398/source/sandbox/QuizEngine/data/questions.json");

        QuizFactory qf = new QuizFactory();

        Quiz quiz = qf.generateQuiz(5);

        Console console = System.console();

        for (int i=0; i<quiz.size(); i++)
        {
            quiz.displayQuestion(i);
            try {
                String input = readLine("Enter answer:");
                Integer key = Integer.parseInt(input);
                quiz.tryAnswerCurrent(key.intValue());
            }
            catch(Exception e)
            {

            }
        }

       quiz.printResults();

    }
}
