package com.Aishwary.httpServer.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{

    private final static Logger LOGGER  = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webRoot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.serverSocket = new ServerSocket(this.port);

    }

    @Override
    public void run() {
        try {
            //multi-threading

            while (serverSocket.isBound() && !serverSocket.isClosed()) {

                //returning a socket
                Socket socket = serverSocket.accept(); //prompts the socket that is listening to a port to accept any connection that arises

                LOGGER.info("Connection Accepted : " + socket.getInetAddress());

                HttpConnectionTheadWorker workerThead = new HttpConnectionTheadWorker(socket);
                workerThead.start();


            }
//            serverSocket.close(); //do not close it keep excepting the connections

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problem with setting up the socket", e);
        } finally {
            if (serverSocket != null){ //it should be null to work
                try {
                    serverSocket.close();
                } catch (IOException e) {} //don't do anything
            }
        }
    }
}
