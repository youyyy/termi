package com.ue.termi.util;/*
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

import com.ue.termi.controller.wx.TextMessage;
import com.ue.termi.entity.WxMsgInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 14:57
 **/
@Mapper
public interface DtoMapper {
    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    @Mapping(expression = "java(textMessage.getFromUserName())",target = "toUserName")
    @Mapping(expression = "java(textMessage.getToUserName())",target = "fromUserName")
//    @Mapping(expression = "java(textMessage.getCreateTime())",target = "createTime")
//    @Mapping(expression = "java(textMessage.getMsgType())",target = "msgType")
//    @Mapping(expression = "java(textMessage.getContent())",target = "content")
    WxMsgInfo req2Entity(TextMessage textMessage);
}
