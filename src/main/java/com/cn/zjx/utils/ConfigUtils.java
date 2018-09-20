package com.cn.zjx.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/6/15
 * @Description: 读取配置文件属性值
 * @Version: 1.0.0
 */
public class ConfigUtils {

    /**
     *  获取指定目录下的文件信息
     * @param path 相对 resources 路径
     * @return
     * @throws IOException
     */
    public static Properties getProperties(String path) throws IOException {
        InputStream resourceAsStream = ConfigUtils.class.getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(resourceAsStream);
        return properties;
    }


}
