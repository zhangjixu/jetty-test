package com.cn.zjx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/9/14
 * @Description: 使用 maven jetty 插件部署项目
 * @Version: 1.0.0
 */
@Controller
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/")
    public String index() {
        LOGGER.error("modelAndView success");
        return "/index.jsp";
    }

    @RequestMapping(value = "/page")
    public String page() {
        LOGGER.error("get method success");
        return "/view/login.jsp";
    }

    @RequestMapping(value = "/data", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String data() {
        LOGGER.error("get data success");
        return "success";
    }



}
