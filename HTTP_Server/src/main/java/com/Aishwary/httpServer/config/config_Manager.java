package com.Aishwary.httpServer.config;

import com.Aishwary.httpServer.util.Json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.net.httpserver.HttpsConfigurator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class config_Manager {

    private static config_Manager myConfigurationManager; //Singleton (single object)
    private static configuration myCurrentConfig;

    public static config_Manager getInstance(){
        if(myConfigurationManager == null){
            myConfigurationManager = new config_Manager();
        }
        return myConfigurationManager;
    }

    /**
     *
     * method to load a configuration file by the path provider
     *
     */
    public void loadConfigFile (String filepath){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1){
                sb.append((char) i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        //when the stringBuffer is completely full with the context of the file
        JsonNode conf = null;
        try {
            conf = Json.pass(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file.", e);
        }
        try {
            myCurrentConfig = Json.fromJson(conf, configuration.class); //assigning
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing the Configuration file internally", e);
        }
    }

    /**
     * Method to get figuration
     * returns the current loaded configuration
     */
    public configuration getCurrentConfig(){ //return type is configuration
        if (myCurrentConfig == null){
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myCurrentConfig;
    }
}
