package ywm.foundation.user.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.foundation.user.model.Group;

/**
 * Created by Herbert Yu on 2019-11-17 18:05
 */
public interface GroupRepository extends WolfMongoRepository<Group, String> {


    @Override
    Group save(Group group);

    @Override
    Group findOne(String id);

    @Override
    void delete(String id);
}
