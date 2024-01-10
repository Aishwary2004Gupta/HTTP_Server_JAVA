package com.Aishwary.httpServer.HTTP;

public class HTTP_ParsingException extends Exception{

    private final HTTP_StatusCode errorCode;

    public HTTP_ParsingException(HTTP_StatusCode errorCode) {
        super(errorCode.MESSAGE);
        this.errorCode = errorCode;
    }

    //Getter

    public HTTP_StatusCode getErrorCode() {
        return errorCode;
    }
}
