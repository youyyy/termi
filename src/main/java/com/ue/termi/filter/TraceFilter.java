package com.ue.termi.filter;/*
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

import com.ue.termi.util.http.HttpRequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author youyi
 * @program termi
 * @description
 * @date 2023-03-14 16:29
 **/
@Component
@Slf4j
public class TraceFilter extends OncePerRequestFilter {

    private static final String KEY_TRACE_ID = "TRACE-ID";
    private static final String CLIENT = "CLIENT";
    private String getTraceId() {
        long timestamp = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();
        String uniqueId = timestamp + uuid.toString().replace("-", "");
        return uniqueId;
    }

    private String getClientInfo(HttpServletRequest request){
        return HttpRequestHelper.getHttpServletRequestKeyValue(CLIENT,request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // KEY_TRACE_ID
        MDC.put(KEY_TRACE_ID, getTraceId());
        MDC.put(CLIENT, getClientInfo(request));

        filterChain.doFilter(request, response);

    }
}
