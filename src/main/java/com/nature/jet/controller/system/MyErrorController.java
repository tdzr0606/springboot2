package com.nature.jet.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * MyErrorController
 *
 * @Author: 竺志伟
 * @Date: 2019-03-19 10:45
 */
@ControllerAdvice
@Controller
@RequestMapping("/error")
@Slf4j
public class MyErrorController extends BasicErrorController
{
    public MyErrorController(ServerProperties serverProperties)
    {
        super(new DefaultErrorAttributes(), serverProperties.getError());
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request)
    {
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<String, Object>();
        if(status.is4xxClientError())
        {
            map.put("message", "页面:" + model.get("path").toString() + "已经丢失,请联系管理员!");
        }
        else if(status.is5xxServerError())
        {
            map.put("message", "页面:" + model.get("path").toString() + "发生系统错误,请联系管理员!");
        }
        else
        {
            map.put("message", model.get("message"));
        }

        log.error("----------------------------------------");
        log.error("Json错误");
        log.error("状态:{}", status.series().value());
        log.error("链接:{}", model.get("path").toString());
        log.error("错误:{}", model.get("message").toString());
        log.error("----------------------------------------");

        return new ResponseEntity<Map<String, Object>>(map, status);
    }

    /**
     * 覆盖默认的HTML响应
     */
    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response)
    {
        //请求的状态
        HttpStatus status = getStatus(request);
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        ModelAndView modelAndView = new ModelAndView();
        if(status.is5xxServerError())
        {
            modelAndView.addObject("errorInfo", model.get("message").toString());
            modelAndView.addObject("uri", model.get("path").toString());
            modelAndView.setViewName("/common/error500");
        }
        else if(status.is4xxClientError())
        {
            modelAndView.addObject("errorInfo", "页面丢失了,程序员悬赏通缉中.....");
            modelAndView.addObject("uri", model.get("path").toString());
            modelAndView.setViewName("/common/error404");
        }

        log.error("----------------------------------------");
        log.error("Html错误");
        log.error("状态:{}", status.series().value());
        log.error("链接:{}", model.get("path").toString());
        log.error("错误:{}", model.get("message").toString());
        log.error("----------------------------------------");

        return modelAndView;
    }
}
