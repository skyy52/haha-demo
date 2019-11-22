package com.qfjy.project.weixin.api.accessToken;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author skyy
 * @version 1.0
 * @date 2019/11/22 19:56
 */

/**
 * 监听,当项目启动时  同时启动获取accesstoken的线程
 */
@WebListener
public class AccessTokenListener implements ServletContextListener {
    // 服务启动时执行的方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("项目启动了");
        //new AccessTokenThread().run();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

