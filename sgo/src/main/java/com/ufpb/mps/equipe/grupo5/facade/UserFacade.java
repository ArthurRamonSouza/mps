package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;

import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.model.User;

public class UserFacade {
    private static UserFacade instance;
    private final UserController userController;

    private UserFacade() {
        this.userController = UserController.getInstance();
    }

    public static synchronized UserFacade getInstance(){
        if(instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public void registerUserCollection(User user) {
        userController.registerUserCollection(user);
    }

    public void registerUserDatabase(User user) {
        userController.registerUserDatabase(user);
    }

    public List<User> listUsersCollection() {
        return userController.listUsersCollection();
    }

    public List<User> listUsersDatabase() {
        return userController.listUsersDatabase();
    }

    public boolean loginUser(String login, String password) {
        return userController.loginUser(login, password);
    }
}