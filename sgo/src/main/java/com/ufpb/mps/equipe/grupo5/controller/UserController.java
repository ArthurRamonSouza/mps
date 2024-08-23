package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.service.UserCollectionService;
import com.ufpb.mps.equipe.grupo5.service.UserDatabaseService;

public class UserController {
    
    private final UserCollectionService userCollectionService;
    private final UserDatabaseService userDatabaseService;

    public UserController() {
        this.userCollectionService = new UserCollectionService();
        this.userDatabaseService = new UserDatabaseService();
    }

    public String registerUserCollection(User user) {
        userCollectionService.save(user);
        return "O usuário foi registrado no banco de dados e na coleção.";
    }

    public  String registerUserDatabase(User user) {
        String message = "O usuário foi registrado no banco de dados.";

        try {
            userDatabaseService.save(user);
        } catch(Error e) {
            return message = "Erro ao slavar o usuário no banco de dados.";
        }

        return message;
    }

    public List<User> listUsersCollection() {
        if(userCollectionService.findAll().isPresent())
            return userCollectionService.findAll().get();

        return new ArrayList<User>();
    }

    public List<User> listUsersDatabase() {
        if(userDatabaseService.findAll().isPresent())
            return userDatabaseService.findAll().get();

        return new ArrayList<User>();
    }
}
