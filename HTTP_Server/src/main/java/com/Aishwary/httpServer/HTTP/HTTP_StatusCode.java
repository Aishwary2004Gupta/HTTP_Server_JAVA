package com.Aishwary.httpServer.HTTP;

public enum HTTP_StatusCode {

    //Bad Requests

    /* --- CLIENT ERRORS --- */
    CLIENT_ERROR_400_BAD_REQUEST(400, "Bad_Request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED(401, "Method_Not_Allowed"),
    CLIENT_ERROR_414_URI_TOO_LONG(414, "URI_Too_Long"),

    /* --- SERVER ERRORS --- */
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED(505, "Http Version Not Supported");

    public final int STATUS_CODE;
    public final String MESSAGE;

    HTTP_StatusCode(int STATUS_CODE, String MESSAGE) {
        this.STATUS_CODE = STATUS_CODE;
        this.MESSAGE = MESSAGE;
    }

}
