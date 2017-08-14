package eu.forapp.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

/**
 *
 * @author Wojtek
 */
public class JsonUtil {
    //private static final Gson gson = new GsonBuilder().create();
    private static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create(); // setPrettyPrinting().

    public static String toJson(Object object){
        return gson.toJson(object);
    }

    public static Object fromJson(String json, Type type){
        return gson.fromJson(json, type);
    }

//    public static Gson gson() {
//        return gson;
//    }
}
