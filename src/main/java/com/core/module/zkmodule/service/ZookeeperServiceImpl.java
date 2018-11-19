package com.core.module.zkmodule.service;

import com.core.module.zkmodule.utils.ZkManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Descript
 * @Author mojianzhang
 * @Date 2018-11-09 11:46:48
 * @Version 1.0
 */
@Component
public class ZookeeperServiceImpl {

    private static Logger logger = LoggerFactory.getLogger(ZookeeperServiceImpl.class);

    @Value("${zookeeper.connection}")
    private String zkconnect;

    /**
     * 检查目录是否存在
     * @param nodePath
     * @return
     */
    public boolean isNodePathExists(String nodePath) {
        boolean isExists = false;
        try {
            CuratorFramework client = ZkManager.getInstance().getClient();

            Stat stat = client.checkExists().forPath(nodePath);
            int version = stat.getVersion();

            logger.info("version ={}", version);
            if(version > 0) {
                isExists = true;
            }
        } catch (Exception e) {

        }
        return isExists;
    }

    /**
     * 创建目录
     * @param nodePath
     * @return
     */
    public boolean createNodePath(String nodePath) {
        boolean isOk = false;
        try {
            CuratorFramework client = ZkManager.getInstance().getClient();

            boolean isPathExists = this.isNodePathExists(nodePath);
            String result = "";
            if(!isPathExists) {
                result = client.create()
                               .creatingParentsIfNeeded()
                               .forPath(nodePath, "test".getBytes());
            }

            logger.info("result ={}", result);
            if(!StringUtils.isEmpty(result)) {
                isOk = true;
            }
        } catch (Exception e) {

        }
        return isOk;
    }

}
