package ywm.foundation.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ywm.foundation.user.model.User;
import ywm.foundation.user.repository.UserRepository;
import ywm.foundation.user.service.UserService;

/**
 * Created by Herbert Yu on 2019-11-17 18:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public User findById(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean delete(String id) {
        userRepository.delete(id);
        return true;
    }
}
