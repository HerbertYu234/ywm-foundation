package ywm.foundation.user.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ywm.foundation.user.model.User;
import ywm.foundation.user.service.UserService;

/**
 * Created by Herbert Yu on 2019-11-17 18:13
 */
@RestController
@RequestMapping("/user")
public class UserServiceApi {

    @Autowired
    private UserService userService;


    @ApiOperation("获取单个user")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findByid(@ApiParam("用户id") @PathVariable("id") String id) {
        return userService.findById(id);
    }

    @ApiOperation("获取单个user，根据用户名")
    @RequestMapping(value = "/get_by_username", method = RequestMethod.GET)
    public User findByUsername(@ApiParam("用户username") @RequestParam String username) {
        return userService.findByUsername(username);
    }
}
