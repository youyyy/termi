package com.ue.termi.interceptor;
/*
          .--,       .--,
         ( (  \.---./  ) )
          '.__/o   o\__.'
             {=  ^  =}
              >  -  <
             /       \
            //       \\
           //|   .   |\\
           "'\       /'"_.-~^`'-.
              \  _  /--'         `
            ___)( )(___
           (((__) (__)))
   高山仰止,景行行止.虽不能至,心向往之。
*/

import com.ue.termi.util.log.ContextUtil;
import com.ue.termi.util.log.ControllerRequest;
import com.ue.termi.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @program: iserver
 * @description: 拦截器
 * @author: youyi
 * @create: 2020-03-17 17:56
 **/
@Slf4j
public class DemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle:请求前调用");
        //返回 false 则请求中断
        Method controllerMethod = null;
        if (handler instanceof HandlerMethod) {
            controllerMethod = ((HandlerMethod) handler).getMethod();
        }
        ControllerRequest controllerRequest = new ControllerRequest(request, controllerMethod);
        ContextUtil.put(ContextUtil.Constant.request, controllerRequest);
        LogUtil.startLog();
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle:请求后调用");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");
        LogUtil.endLog();
        ContextUtil.removeAll();
    }

}
