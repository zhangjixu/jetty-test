package com.cn.test;

import com.cn.zjx.utils.ReflectUtils;
import org.junit.Test;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/9/19
 * @Description:
 * @Version: 1.0.0
 */
public class TestPath {

    @Test
    public void test() {
        // 获取 warFile 真实路径
        String warFile = ReflectUtils.ME.getExternalForm();
        String realPath = ReflectUtils.ME.getRealPath();

        System.out.println("warFile ==================== : " + warFile);
        System.out.println("realPath ==================== : " + realPath);

    }

    @Test
    public void testPath() {
       String str = "";
       String[] arr = str.split(",");
       for (String string : arr) {
           String[] data = string.split(":");
           System.out.println(data[0]);
           System.out.println(data[1]);
       }

    }

}
