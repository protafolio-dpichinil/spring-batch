package cl.dpichinil.test.springbatch.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Parse {
    public static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public static <T> T JsonToObject(String json, Class<T> classType){
        try {
            return (T) Parse.JSON_MAPPER.readValue(json, classType);
        }catch(Exception e){
            return null;
        }
    }

    public static String ObjectToJson(Object object) {
        try {
            return Parse.JSON_MAPPER.writeValueAsString(object);
        }catch(Exception e){
            return null;
        }
    }
}
