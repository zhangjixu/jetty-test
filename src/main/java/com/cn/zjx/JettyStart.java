package com.cn.zjx;

import com.cn.zjx.utils.ConfigUtils;
import com.cn.zjx.utils.ReflectUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/9/14
 * @Description: jetty 服务启动
 * @Version: 1.0.0
 */
public class JettyStart {

    private static String deploy;

    static {
        try {
            Properties properties = ConfigUtils.getProperties("/config/deploy.properties");
            deploy = properties.getProperty("env");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {
        if ("dev".equals(deploy))
         devEnvironment();
        else if ("pro".equals(deploy))
            proEnvironment();

        System.out.println(" ================== " + deploy);
    }

    /**
     * 开发环境
     */
    public static void devEnvironment() throws Exception {
        // 1.创建 Server 对象
        Server server = new Server();
        // 开启 jvm 回调钩子，在 jvm 关闭时先关闭服务器
        server.setStopAtShutdown(true);
        // 2. 创建 Connector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        // 在 window 需要注意，关闭地址多用的操作
        //connector.setReuseAddress(false);
        // 3.将 Connector 添加到 server 中
        server.setConnectors(new Connector[]{connector});

        // 4.创建 web 上下文
        String webappPath = "src/main/webapp";
        WebAppContext ctx = new WebAppContext(webappPath, "/");


        // 设置 web.xml 的位置
        ctx.setDescriptor(webappPath + "/WEB_INF/web.xml");
        // 设置 web 根目录
        ctx.setResourceBase(webappPath);
        // 设置类加载器
        ctx.setClassLoader(Thread.currentThread().getContextClassLoader());

        // 5.将 web 上下文与服务管理起来
        server.setHandler(ctx);
        // 6. 启动服务器
        server.start();
        // 7.将子线程加入主线程
        server.join();
    }

    /**
     * 部署环境
     */
    public static void proEnvironment() throws Exception {
        // 1.创建 Server 对象
        Server server = new Server();
        // 开启 jvm 回调钩子，在 jvm 关闭时先关闭服务器
        server.setStopAtShutdown(true);
        // 2. 创建 Connector
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8080);
        // 在 window 需要注意，关闭地址多用的操作
        //connector.setReuseAddress(false);
        // 3.将 Connector 添加到 server 中
        server.setConnectors(new Connector[]{connector});

        // 获取 warFile 真实路径
        String warFile = ReflectUtils.ME.getExternalForm();
        String realPath = ReflectUtils.ME.getRealPath();
        WebAppContext ctx = new WebAppContext(warFile, "/");
        ctx.setServer(server);

        System.out.println(" ===================== " + warFile);
        System.out.println(" ===================== " + realPath);

        String currentDir = new File(realPath).getParent();
        currentDir = URLDecoder.decode(currentDir, "UTF-8");
        File workDir = new File(currentDir, "work");
        ctx.setTempDirectory(workDir);


        // 5.将 web 上下文与服务管理起来
        server.setHandler(ctx);
        // 6. 启动服务器
        server.start();


        // 7.将子线程加入主线程
        server.join();
    }

}
