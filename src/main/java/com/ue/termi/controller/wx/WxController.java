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

import com.ue.termi.biz.WxMessageService;
import com.ue.termi.util.WxUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 11:22
 **/
@RestController
@RequestMapping("/wx")
public class WxController {

    @Resource
    private WxMessageService wxMessageService;
    @GetMapping("/checkValid")
    public String testMessageHandler(
            @RequestParam(name = "signature") String signature,
            @RequestParam(name = "timestamp") String timestamp,
            @RequestParam(name = "nonce") String nonce,
            @RequestParam(name = "echostr") String echostr) {
        String res = WxUtil.checkToken(timestamp, nonce);
        if (Objects.equals(res,signature)){
            return echostr;
        }
        return "failed";
    }

    @PostMapping("/checkValid")
    public String messageHandler(HttpServletRequest request, HttpServletResponse response) {
        return wxMessageService.messageHandle(request, response);
    }

}
