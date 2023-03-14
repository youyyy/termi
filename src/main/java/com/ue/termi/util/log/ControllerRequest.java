package com.ue.termi.util.log;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author yilinjun
 * @date 2022/7/8 17:42
 */
@Data
public class ControllerRequest implements Serializable {

    private String httpMethod;

    private String uri;

    private final Map<String, String> parameters;

    private final Map<String, String> header;

    private Object body;

    private final Method method;

    private String userId;

    private String xtoken;



    public ControllerRequest(HttpServletRequest request, Method controllerMethod) {
        httpMethod = request.getMethod();
        uri = request.getRequestURI();
        parameters = RequestUtil.getParameters(request);
        header = RequestUtil.getHeaders(request);
        method = controllerMethod;
    }
}
