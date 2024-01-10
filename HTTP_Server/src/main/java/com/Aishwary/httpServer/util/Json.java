package com.Aishwary.httpServer.util;
//passing Json as java -> using jackson

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {
    //need an object mapper
    private static ObjectMapper myObjectMap = defaultObjectMap(); //variable calling this method

    //creating a method that creates a new object mapper
    private static ObjectMapper defaultObjectMap(){
        ObjectMapper oM = new ObjectMapper();
        oM.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return oM;
    }

    //creating a method to pass a JSON string into a JSON node
    public static JsonNode pass(String jsonSource) throws IOException {
        return myObjectMap.readTree(jsonSource);
    }

    //Moving this JSON into configuration(POJO)
    //Using generic as we don't know what type of object will it return
    public static <T> T fromJson(JsonNode node, Class<T> clazz) throws JsonProcessingException { // class that we want to transfer it into
        return myObjectMap.treeToValue(node, clazz);
    }

    //getting this json file into json node
    public static JsonNode toJson(Object obj){
        return myObjectMap.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    //to see the Json node in a String format
    private static String generateJson(Object ob, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMap.writer();
        if (pretty){
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT); //to make it look pretty
        }
        return objectWriter.writeValueAsString(ob);
    }
}
