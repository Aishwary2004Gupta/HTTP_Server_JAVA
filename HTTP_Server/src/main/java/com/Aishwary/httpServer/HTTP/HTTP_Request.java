package com.Aishwary.httpServer.HTTP;

public class HTTP_Request extends HTTP_Message {

    private HTTP_Method method;
    private String requestTarget;
    private String originalHttpVersion; //literal from the request
    //new variable
    private HTTP_Version bestCompatibleHTTP_Version;

    HTTP_Request() { //access modifier -> package level (all the classes in this HTTP package can only use it)
    }

    //RFC7231

    //Getter and setter
    public HTTP_Method getMethod() {
        return method;
    }

    // Getter fo requestTarget

    public String getRequestTarget() {
        return requestTarget;
    }

    // Getter for HTTP_Version

    public HTTP_Version getBestCompatibleHttpVersion() {
        return bestCompatibleHTTP_Version;
    }

    public String getOriginalHttpVersion() {
        return originalHttpVersion;
    }

    //Modified the request method
    void setMethod(String methodName) throws HTTP_ParsingException {
        for (HTTP_Method method : HTTP_Method.values()){
            if (methodName.equals(method.name())){
                this.method = method;
                return;
            }
        }
        throw new HTTP_ParsingException(
                HTTP_StatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED
        );
    }

    void setRequestTarget(String requestTarget) throws HTTP_ParsingException    {
        if (requestTarget == null || requestTarget.length() == 0){
            throw new HTTP_ParsingException(HTTP_StatusCode.SERVER_ERROR_500_INTERNAL_SERVER_ERROR);
        }
        this.requestTarget = requestTarget;
    }
    //RFC7230

    //setter for the best compatible http version

    void setHttpVersion(String originalHttpVersion) throws BadHTTPVersionException, HTTP_ParsingException {
        this.originalHttpVersion = originalHttpVersion;
        //also calling the best HTTP version
        this.bestCompatibleHTTP_Version = HTTP_Version.getBestCompatibleVersion(originalHttpVersion);
        if (this.bestCompatibleHTTP_Version == null){ //there is no best compatible version to work with
            throw new HTTP_ParsingException(HTTP_StatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
        }
    }
}
