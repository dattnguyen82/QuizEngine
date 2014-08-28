package QuizEngine;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by 212391398 on 8/15/14.
 */
public class QuestionBankManager
{
    private static QuestionBankManager instance;

    private static JSONParser parser;
    private static ArrayList<Question> questions;
    private static Random randomGenerator;


    private QuestionBankManager()
    {
        parser = new JSONParser();
        questions = new ArrayList<Question>();
        randomGenerator = new Random();
    }

    public static QuestionBankManager getInstance()
    {
        if (instance == null)
        {
            instance = new QuestionBankManager();
        }
        return instance;
    }

    public static void LoadFromJsonFile(String fileName)
    {
        try
        {
            Object obj = parser.parse(new FileReader(fileName));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray companyList = (JSONArray) jsonObject.get("questions");
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext())
            {
                JSONObject jobj = iterator.next();

                String text = (String) jobj.get("text");
                Long type = (Long) jobj.get ("type");
                Long key = (Long) jobj.get("key");

                questions.add(new Question(text, type.intValue(), key.intValue()));
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static int size()
    {
        return questions.size();
    }

    public static Question getQuestion(int index)
    {
        if (index < 0 || index > size()-1)
        {
            return null;
        }

        return questions.get(index);
    }

    public static Question getRandomQuestion()
    {
        int index = randomGenerator.nextInt(QuestionBankManager.getInstance().size());
        return questions.get(index);
    }

    public static ArrayList<Question> generateQuestionSet(int count)
    {
        ArrayList<Question> returnSet = new ArrayList<Question>();
        int size = size();

        while (returnSet.size() < count)
        {
            int index = randomGenerator.nextInt(size);
            Question q = questions.get(index);

            if (!returnSet.contains(q))
            {
                returnSet.add(q);
            }

        }

        return returnSet;
    }

}

