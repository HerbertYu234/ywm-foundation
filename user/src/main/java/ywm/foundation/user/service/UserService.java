package ywm.foundation.user.service;


import ywm.foundation.user.model.User;

/**
 * Created by Herbert Yu on 2019-11-17 18:04
 */
public interface UserService {

    User findByUsername(String username);

    User findById(String id);

    User save(User user);

    boolean delete(String id);
}
