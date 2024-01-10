package com.Aishwary.httpServer;
// what we need to configure
// where all the files are going to live (string)
import com.Aishwary.httpServer.config.config_Manager;
import com.Aishwary.httpServer.config.configuration;
import com.Aishwary.httpServer.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;

/**
 * Maven handles the dependencies automatically
 * Maven is an automation tool that is used to build java projects
 * Driver class for HTTP Server
 *
 */

public class Http_Server {

    private final static Logger LOGGER  = LoggerFactory.getLogger(Http_Server.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Server...");

        //Finally calling it
        config_Manager.getInstance().loadConfigFile("src/main/resources/Http.json");
        configuration conf = config_Manager.getInstance().getCurrentConfig();

        LOGGER.info("Using Port: " + conf.getPort());
        LOGGER.info("Using webRoot: " + conf.getWebRoot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebRoot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            //will be handling later
        }
    }
}
