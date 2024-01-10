package com.Aishwary.httpServer.config;
//databind is used to transfer the JSON file into this POJO (Plain Old Java Object)
public class configuration { //the file that will be mapped to json file

    private int port;
    private String webRoot;

    //get and set

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }
}
