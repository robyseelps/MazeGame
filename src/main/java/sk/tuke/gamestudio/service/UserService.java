package sk.tuke.gamestudio.service;

import sk.tuke.gamestudio.entity.User;

public interface UserService {
    User getUserFromLogin(String login) throws UserException;
    User getUserFromLoginAndPass(String login, String password) throws UserException;
    void addUser(User user) throws UserException;
}
