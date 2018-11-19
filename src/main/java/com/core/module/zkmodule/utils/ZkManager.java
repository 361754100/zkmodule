package com.core.module.zkmodule.utils;

import com.core.module.zkmodule.common.SpringContext;
import com.core.module.zkmodule.config.PropertiesConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * @Descript
 * @Author mojianzhang
 * @Date 2018-11-15 14:24:50
 * @Version 1.0
 */
public class ZkManager {

    private static ZkManager instance = null;

    private static CuratorFramework client = null;

    private ZkManager() {}

    private static class HolderClass {
        private static ZkManager manager = new ZkManager();
    }

    public static ZkManager getInstance() {
        if(instance == null) {
            PropertiesConfig config = SpringContext.getBean(PropertiesConfig.class);
            instance = HolderClass.manager;
            client = CuratorFrameworkFactory.builder().connectString(config.getZkconnection())
                    .sessionTimeoutMs(1000)    // 连接超时时间
                    .connectionTimeoutMs(1000) // 会话超时时间
                    // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    // 指定业务的命名空间
                    .namespace("zkmodule")
                    .build();

            client.start();
        }
        return instance;
    }

    public CuratorFramework getClient() {
        return client;
    }

    public void closeClient() {
        if(client != null) {
            CloseableUtils.closeQuietly(client);
        }
    }
}
