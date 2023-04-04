package com.ue.termi.biz;/*
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 14:02
 **/
public interface WxMessageService {
    String messageHandle(HttpServletRequest request, HttpServletResponse response);
}
