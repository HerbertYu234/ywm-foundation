package ywm.foundation.user.repository;

import com.wolf.mongo.repository.WolfMongoRepository;
import ywm.foundation.user.model.User;

/**
 * Created by Herbert Yu on 2019-11-17 18:02
 */
public interface UserRepository extends WolfMongoRepository<User, String> {

    @Override
    User save(User user);

    @Override
    User findOne(String id);

    User findOneByUsername(String username);

    @Override
    void delete(String id);
}
