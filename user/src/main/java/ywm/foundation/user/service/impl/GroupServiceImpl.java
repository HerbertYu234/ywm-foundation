package ywm.foundation.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ywm.foundation.user.model.Group;
import ywm.foundation.user.repository.GroupRepository;
import ywm.foundation.user.service.GroupService;

/**
 * Created by Herbert Yu on 2019-11-17 18:06
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group findById(String id) {
        return groupRepository.findOne(id);
    }

    @Override
    public Group save(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public boolean delete(String id) {
        groupRepository.delete(id);
        return true;
    }
}
