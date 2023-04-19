package ro.ubb.thesis.app.config.security.user.service;

import ro.ubb.thesis.app.config.security.user.domain.UserEntity;

public interface UserService {

    UserEntity saveUser(UserEntity user);

    UserEntity findUser(String username);

}
