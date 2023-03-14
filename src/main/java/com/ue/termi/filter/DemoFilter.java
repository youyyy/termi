package com.ue.termi.filter;
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

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * @program: iserver
 * @description: 过滤器
 * @author: youyi
 * @create: 2020-03-17 17:27
 **/
//@WebFilter(filterName = "demoFilter",urlPatterns = {"/demo"})
@Slf4j
public class DemoFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter 初始化");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter 请求处理");
        //执行这一句，说明放行（让下一个过滤器执行，或者执行目标资源）
        filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {
        log.info("filter 销毁");

    }
}
