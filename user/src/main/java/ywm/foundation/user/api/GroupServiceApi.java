package ywm.foundation.user.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ywm.foundation.user.service.GroupService;

/**
 * Created by Herbert Yu on 2019-11-17 18:13
 */
@RestController
@RequestMapping("/group")
public class GroupServiceApi {

    @Autowired
    private GroupService groupService;


}
