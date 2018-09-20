package com.cn.zjx.controller;

import com.cn.zjx.utils.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/9/14
 * @Description: 使用 jetty 嵌入应用的方式启动
 * @Version: 1.0.0
 */
@Controller
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @RequestMapping(value = "/")
    public String index() {
        String warFile = ReflectUtils.ME.getExternalForm();
        String realPath = ReflectUtils.ME.getRealPath();
        LOGGER.error(" ============ warFile : {}", warFile);
        LOGGER.error(" ============ realPath : {}", realPath);
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

    @RequestMapping(value = "/mysql", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Object getMysql() {
      String sql = "select * from job limit 1;";
      List list = mysqlJdbcTemplate.queryForList(sql, Map.class);
      System.out.println(list.get(0));
      return list.get(0);
    }



}
