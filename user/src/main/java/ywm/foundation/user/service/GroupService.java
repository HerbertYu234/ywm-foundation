package ywm.foundation.user.service;


import ywm.foundation.user.model.Group;

/**
 * Created by Herbert Yu on 2019-11-17 18:06
 */
public interface GroupService {

    Group findById(String id);

    Group save(Group group);

    boolean delete(String id);
}
