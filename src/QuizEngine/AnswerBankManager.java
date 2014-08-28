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
 * Created by 212391398 on 8/18/14.
 */
public class AnswerBankManager {

    private static AnswerBankManager instance;

    private static JSONParser parser;
    private static ArrayList<Answer> answers;
    private static Random randomGenerator;


    private AnswerBankManager()
    {
        parser = new JSONParser();
        answers = new ArrayList<Answer>();
        randomGenerator = new Random();
    }

    public static AnswerBankManager getInstance()
    {
        if (instance == null)
        {
            instance = new AnswerBankManager();
        }
        return instance;
    }

    public static void LoadFromJsonFile(String fileName, String rootName)
    {
        try
        {
            Object obj = parser.parse(new FileReader(fileName));

            JSONObject jsonObject = (JSONObject) obj;
            Long type = (Long) jsonObject.get("type");
            JSONArray companyList = (JSONArray) jsonObject.get(rootName);
            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext())
            {
                JSONObject jobj = iterator.next();

                String text = (String) jobj.get("text");
                Long key = (Long) jobj.get("key");

                answers.add(new Answer(text, type.intValue(), key.intValue()));
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
        return answers.size();
    }

    public static Answer getAnswer(int index)
    {
        if (index < 0 || index > size()-1)
        {
            return null;
        }

        return answers.get(index);
    }

    public static Answer getAnswerByKey(int type, int key)
    {
        Answer ret = null;
        Iterator<Answer> iterator = answers.iterator();

        while(iterator.hasNext())
        {
            ret = (Answer) iterator.next();

            if (ret.type == type && ret.key == key)
            {
                return ret;
            }
        }

        return ret;
    }

    public static ArrayList<Answer> generateAnswerChoices(int type, int key, int count)
    {
        ArrayList<Answer> keys = new ArrayList<Answer>();
        int size = size();

        keys.add(getAnswerByKey(type, key));

        while (keys.size() < count)
        {
            int index = randomGenerator.nextInt(size);
            Answer a = answers.get(index);

            if (!keys.contains(a))
            {
                keys.add(a);
            }

        }

        return keys;
    }

}
