package com.Aishwary.httpServer.HTTP;

public enum HTTP_Method {
    GET, HEAD;
    public static final int MAX_LENGTH;

    //Assigning ⬆️ it using a static block
    static {
        int tempMaxLength = -1;
        for (HTTP_Method method: values()){
            if (method.name().length() > tempMaxLength){
                tempMaxLength = method.name().length();
            }
        }
        MAX_LENGTH = tempMaxLength;
    }
}
