package com.core.module.zkmodule.controller;

import com.core.module.zkmodule.service.ZookeeperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Descript
 * @Author mojianzhang
 * @Date 2018-11-13 11:31:56
 * @Version 1.0
 */
@Controller
@RequestMapping(value = "command")
public class CommandController {

    @Autowired
    private ZookeeperServiceImpl zookeeperService;

    @RequestMapping(value = "/check_node_path", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkNodePath(@RequestParam(required = true) String nodePath) {
        boolean isExists = zookeeperService.isNodePathExists(nodePath);

        return isExists;
    }

    @RequestMapping(value = "/create_path", method = RequestMethod.GET)
    @ResponseBody
    public boolean createPath(@RequestParam(required = true) String nodePath) {
        boolean isExists = zookeeperService.createNodePath(nodePath);

        return isExists;
    }

}
