package com.ue.termi.exception;

/**
 * @author sunkuankuan
 */
public class PathErrorException extends IException {

    private final String method;

    private final String requestUri;

    public PathErrorException(String method, String requestURI) {
        this.method = method;
        this.requestUri = requestURI;
    }

    public String getMethod() {
        return method;
    }

    public String getRequestUri() {
        return requestUri;
    }
}
