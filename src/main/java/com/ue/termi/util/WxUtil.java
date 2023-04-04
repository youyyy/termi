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

import cn.hutool.crypto.digest.DigestUtil;
import org.thymeleaf.expression.Lists;
import sun.security.rsa.RSASignature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-04-04 11:38
 **/
public class WxUtil {
    /**
     * 公众号token
     */
    private static final String token = "a702def9e1e46f75df573b98748b4c83";

    public static String checkToken(String timestamp, String nonce){
        List<String> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        StringBuilder sb = new StringBuilder();
        list.stream().sorted().forEach(sb::append);
        return DigestUtil.sha1Hex(sb.toString());
    }

}
