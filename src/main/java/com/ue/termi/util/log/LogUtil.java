package com.ue.termi.util.log;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * @author yilinjun
 * @date 2022/7/8 17:43
 */
@Slf4j
public class LogUtil {
    public static void startLog() {
        ControllerRequest controllerRequest = (ControllerRequest) ContextUtil.get(ContextUtil.Constant.request);
        if (null != controllerRequest) {
            log.info("This is a request start.uri={},user={},userId={},xtoken={},param={},body={}",
                    controllerRequest.getUri(),
                    Objects.nonNull(controllerRequest.getHeader()) ? controllerRequest.getHeader().getOrDefault("x-login-session", null) : null,
                    controllerRequest.getUserId(),
                    controllerRequest.getXtoken(),
                    JSONUtil.parse(controllerRequest.getParameters()),
                    JSONUtil.parse(controllerRequest.getBody()));
        } else {
            log.info("This is a request start.uri={},userId={},param={},body={}", null, null, null, null);
        }
    }
    public static void endLog() {
        ControllerRequest controllerRequest = (ControllerRequest) ContextUtil.get(ContextUtil.Constant.request);
        Object response = ContextUtil.get(ContextUtil.Constant.response);
        if (null != controllerRequest) {
            log.info("This is a request response.uri={},user={},userId={},xtoken={},param={},body={},response={}",
                    controllerRequest.getUri(),
                    Objects.nonNull(controllerRequest.getHeader()) ? controllerRequest.getHeader().getOrDefault("x-login-session", null) : null,
                    controllerRequest.getUserId(),
                    controllerRequest.getXtoken(),
                    JSONUtil.parse(controllerRequest.getParameters()),
                    JSONUtil.parse(controllerRequest.getBody()),
                    JSONUtil.parse(response));
        } else {
            log.info("This is a request response.uri={},userId={},param={},body={},response={}", null, null, null, null, JSONUtil.parse(response));
        }
    }
}
