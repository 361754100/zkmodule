package com.core.module.zkmodule.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Descript 自定义配置-实体类映射
 * @Author mojianzhang
 * @Date 2018-07-17 11:15:48
 * @Version 1.0
 */
@Configuration
public class PropertiesConfig {

    @Value("${zookeeper.connection}")
    private String zkconnection;

    public String getZkconnection() {
        return zkconnection;
    }

    public void setZkconnection(String zkconnection) {
        this.zkconnection = zkconnection;
    }
}
