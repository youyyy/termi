package com.ue.termi.util.http;/*
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-03-14 16:37
 **/
public class HttpRequestHelper {
    public static String getHttpServletRequestKeyValue(String key, HttpServletRequest httpRequest) {
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            if (key.equals(name)) {
                return httpRequest.getHeader(key);
            }
        }

        Cookie[] cookies = httpRequest.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if (key.equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return httpRequest.getParameter(key);
    }
}
