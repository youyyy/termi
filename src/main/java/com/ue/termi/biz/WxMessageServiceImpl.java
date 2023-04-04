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

import cn.hutool.json.JSONUtil;
import com.ue.termi.controller.wx.TextMessage;
import com.ue.termi.biz.WxMessageService;
import com.ue.termi.entity.WxMsgInfo;
import com.ue.termi.service.WxMsgInfoService;
import com.ue.termi.util.DtoMapper;
import com.ue.termi.util.WxMessageUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 14:02
 **/
@Service
public class WxMessageServiceImpl implements WxMessageService {
    @Resource
    private WxMsgInfoService wxMsgInfoService;
    /**
     * 微信消息格式
     * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Receiving_standard_messages.html
     * @param request
     * @param response
     * @return
     */
    @Override
    public String messageHandle(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> parseXml = WxMessageUtil.parseXml(request);
        //
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(parseXml.get("FromUserName"));
        textMessage.setFromUserName(parseXml.get("ToUserName"));
        Date date = new Date();
        textMessage.setCreateTime(date.getTime());
        textMessage.setMsgType(WxMessageUtil.RESP_MESSAGE_TYPE_TEXT);
        if ("text".equals(parseXml.get("MsgType"))){
            // todo 转换
            String req = parseXml.get("Content");
            String res = "游移说：" + req;
            textMessage.setContent(res);
        }else {
            textMessage.setContent("目前仅支持文本呦~");
        }

        WxMsgInfo wxMsgInfo = DtoMapper.INSTANCE.req2Entity(textMessage);
        wxMsgInfo.setWxSnapshot(JSONUtil.toJsonPrettyStr(parseXml));
        wxMsgInfoService.save(wxMsgInfo);
        return WxMessageUtil.textMessageToXml(textMessage);
    }
}
