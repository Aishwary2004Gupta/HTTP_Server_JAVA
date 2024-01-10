package com.Aishwary.httpServer.HTTP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HTTP_Parser {
    private final static Logger LOGGER = LoggerFactory.getLogger(HTTP_Parser.class);

    //Request line      //hex-decimal
    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    public HTTP_Request parseHttpRequest(InputStream inputStream) throws HTTP_ParsingException{
        //modifying it

        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        //Making object
        HTTP_Request request = new HTTP_Request();

        try {
            parseRequestLine(reader, request); //cannot pass it directly (using InputStreamReader)
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseHeader(reader, request);
        parseBody(reader, request);

        return request;

    }

    private void parseBody(InputStreamReader reader, HTTP_Request request) {

    }

    private void parseHeader(InputStreamReader reader, HTTP_Request request) {

    }

    private void parseRequestLine(InputStreamReader reader, HTTP_Request request) throws IOException, HTTP_ParsingException {

        StringBuilder processingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requestTargetParsed = false;

        //loop for the CRLF(Carriage return line feed features)
        int _byte;
        while ((_byte = reader.read()) >= 0) {
            if (_byte == CR) {
                _byte = reader.read();
                if (_byte == LF) {
                    LOGGER.debug("Requesting Line Version To Process : {}", processingDataBuffer.toString());
                    //if method or the target request is not processed returning a bad request
                    if (!methodParsed || !requestTargetParsed){
                        throw new HTTP_ParsingException(HTTP_StatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }

                    //after the line feed if found
                    //update the request with the HTTP Version
                    try {
                        request.setHttpVersion(processingDataBuffer.toString());
                    } catch (BadHTTPVersionException e) {
                        throw new HTTP_ParsingException(HTTP_StatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                    }

                    return;
                } else {
                    throw new HTTP_ParsingException(HTTP_StatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
            }

            if (_byte == SP){
//Process Previous data
                if (!methodParsed){ // if method not parsed
                    LOGGER.debug("Requesting Line Method To Process : {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                } else if (!requestTargetParsed) {
                    LOGGER.debug("Requesting Line Request Target To Process : {}", processingDataBuffer.toString());
                    request.setRequestTarget(processingDataBuffer.toString());
                    requestTargetParsed = true;
                } else {
                    throw new HTTP_ParsingException(HTTP_StatusCode.CLIENT_ERROR_400_BAD_REQUEST);
                }
                processingDataBuffer.delete(0, processingDataBuffer.length());
            } else {
                processingDataBuffer.append((char) _byte);
                if (!methodParsed){
                    if (processingDataBuffer.length() > HTTP_Method.MAX_LENGTH){
                        throw new HTTP_ParsingException(HTTP_StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                    }
                }
            }
        }
    }
}
