//package com.ue.termi.controller;
//
//import com.ue.termi.exception.PathErrorException;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author sunkuankuan
// */
//@Controller
//public class PathErrorController implements ErrorController {
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
//
//    @RequestMapping("/error")
//    @ResponseBody
//    public void error(HttpServletRequest request){
//        throw new PathErrorException(request.getMethod(),request.getRequestURI());
//    }
//}
