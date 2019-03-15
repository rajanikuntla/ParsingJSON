import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import java.io.FileReader;
import java.util.*;


//This is the main class which takes in json object and transforms in to a correct output
public class ParseStructureMain {

    public static Map<Long,Structure> structList = new HashMap<Long, Structure>();
    public static Long root;
    public static void main(String[] args) throws Exception{
        JSONParser parser = new JSONParser();
            Object object = parser
                    .parse(new FileReader("C:\\Users\\Rajani\\Desktop\\structure.json"));
            JSONObject jsonObject = (JSONObject)object;
            System.out.println("Before Transforming....");
            System.out.println(jsonObject);
            //This method adds each structure object from the given json object to a map
            JsonMapConverter.addToList(jsonObject);
            //This method gets the required output in format mentioned in the question
            Structure obj = JsonMapConverter.transformJson(structList);

            Gson gson = new GsonBuilder().create();
            String json = gson.toJson(obj);
            System.out.println("After Transforming....");
            System.out.println(json);


    }

}
