package com.cn.zjx.utils;

import java.net.URL;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/9/19
 * @Description:
 * @Version: 1.0.0
 */
public enum ReflectUtils {

    ME;
    private void ReflectUtils() {

    }

    /**
     *  获取 URL 字符串展示形式
     * @return
     */
    public String getExternalForm() {
        return getURL().toExternalForm();
    }

    /**
     *  获取真实路径
     * @return
     */
    public String getRealPath() {
        return getURL().getPath();
    }

    private URL getURL() {
        return this.getClass().getProtectionDomain().getCodeSource().getLocation();
    }
}
