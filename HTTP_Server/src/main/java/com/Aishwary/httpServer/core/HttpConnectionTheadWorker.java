package com.Aishwary.httpServer.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectionTheadWorker extends Thread{

    private final static Logger LOGGER  = LoggerFactory.getLogger(HttpConnectionTheadWorker.class);
    private Socket socket;

    public HttpConnectionTheadWorker(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        OutputStream outputStream = null;



        try {
            inputStream = socket.getInputStream(); //reading
            outputStream = socket.getOutputStream(); //writing

            //Implementing HTTP protocol
            /*

            int _byte;

            while ((_byte = inputStream.read()) >= 0){
                System.out.println((char) _byte);
            }
            */

            //defining the page that will be sent/showcased to the browser
            //creating a html page
            //nothing to be read only writing part required
            String html = "<html><head><title>HTTP-server using java BACKEND</title></head><body><h1>This page showcases a Http server build with java</h1></body></html>";

            //sending two special characters (carriage return and the line feed)
            final String CRLF = "\r\n"; //13, 10

            String response = // Status Line : HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                    "HTTP/1.1 200 OK" + CRLF +
                    "Content-Length: " + html.getBytes().length + CRLF + //HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());

            LOGGER.info(" * Connection Processing finished");
        } catch (IOException e) {
            LOGGER.error("Problem with the communication", e); //pass the exception to the LOGGER
            throw new RuntimeException(e);
            //Work on this later
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {}
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
    }
}
