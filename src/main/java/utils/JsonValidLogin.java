package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class JsonValidLogin {

    public static Object[][] getJsonData(String Json_Path, String Json_Data, int Json_attr)
            throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader(Json_Path));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray) jsonObject.get(Json_Data);

        Object[][] arr = new String[jsonArray.size()][Json_attr];
        for (int i=0 ; i <jsonArray.size(); i++)
        {
            JSONObject obj1= (JSONObject) jsonArray.get(i);
            arr[i][0] = java.lang.String.valueOf(obj1.get("username"));
            arr[i][1] = java.lang.String.valueOf(obj1.get("password"));
            arr[i][2] = java.lang.String.valueOf(obj1.get("HeaderTxt"));
        }
        return arr;
    }
}
