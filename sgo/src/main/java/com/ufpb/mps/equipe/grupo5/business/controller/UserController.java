package com.ufpb.mps.equipe.grupo5.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.factory.UserServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.util.template.LoginValidator;
import com.ufpb.mps.equipe.grupo5.util.template.PasswordValidator;

public class UserController {
    
    private static UserController instance;
    
    private Service<User> userService;

    public static synchronized UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public void registerUserCollection(User user) {
        try {
            LoginValidator.validateLogin(user.getLogin());
            PasswordValidator.validatePassword(user);

            this.userService = UserServiceFactory.createRepository("collection");
            this.userService.save(user);

            System.out.println("Usuário registrado com sucesso na coleção.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário na coleção: " + e.getMessage());
        }
    }

    public void registerUserDatabase(User user) {
        try {
            LoginValidator.validateLogin(user.getLogin());
            PasswordValidator.validatePassword(user);
           
            this.userService = UserServiceFactory.createRepository("database");
            this.userService.save(user);
            
            System.out.println("Usuário registrado com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar usuário no banco de dados: " + e.getMessage());
        }
    }

    public void updateUserCollection(User user) {
        try {
            LoginValidator.validateLogin(user.getLogin());
            PasswordValidator.validatePassword(user);

            this.userService = UserServiceFactory.createRepository("collection");
            this.userService.update(user);
            
            System.out.println("Usuário atualizado com sucesso na coleção.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário na coleção: " + e.getMessage());
        }
    }

    public void updateUserData(User user) {
        try {
            LoginValidator.validateLogin(user.getLogin());
            PasswordValidator.validatePassword(user);

            this.userService = UserServiceFactory.createRepository("database");
            this.userService.save(user);
            
            System.out.println("Usuário atualizado com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar usuário no banco de dados: " + e.getMessage());
        }
    }

    public void deleteUserDatabase(User user) {
        try {
            this.userService = UserServiceFactory.createRepository("database");
            this.userService.delete(user);
        } catch (Exception e) {
            System.out.println("Erro ao tentar remover o usuário do banco de dados.");
        }
    }

    public void deleteUserCollection(User user) {
        try {
            this.userService = UserServiceFactory.createRepository("collection");
            this.userService.delete(user);
        } catch (Exception e) {
            System.out.println("Erro ao tentar remover o usuário da coleção.");
        }
    }

    public List<User> listUsersCollection() {
        this.userService = UserServiceFactory.createRepository("collection");
        return this.userService.findAll().orElse(new ArrayList<User>());
    }

    public List<User> listUsersDatabase() {
        this.userService = UserServiceFactory.createRepository("database");
        return this.userService.findAll().orElse(new ArrayList<User>());
    }

    public User getUserDatabaseByLogin(String login) {
        this.userService = UserServiceFactory.createRepository("database");
        return this.userService.findBy(login).get();
    }

    public User getUserCollectionByLogin(String login) {
        this.userService  = UserServiceFactory.createRepository("collection:");
        return this.userService.findBy(login).get();
    }
    public boolean loginUser(String login, String password) {
        this.userService = UserServiceFactory.createRepository("database");
        return this.userService.login(login, password);
    }
}
