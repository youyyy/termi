package com.ue.termi.controller.wx;/*
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

import lombok.Data;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 11:26
 **/
@Data
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private String CreateTime;
    private String MsgType;
    private String Content;
}
