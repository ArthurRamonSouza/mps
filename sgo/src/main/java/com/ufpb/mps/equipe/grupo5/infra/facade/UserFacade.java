package com.ufpb.mps.equipe.grupo5.infra.facade;

import java.util.List;
import com.ufpb.mps.equipe.grupo5.comand.Command;
import com.ufpb.mps.equipe.grupo5.comand.DeleteUserCommand;
import com.ufpb.mps.equipe.grupo5.comand.RegisterUserCommand;
import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.infra.memento.Caretaker;
import com.ufpb.mps.equipe.grupo5.model.User;

public class UserFacade {
    private static UserFacade instance;
    private final UserController userController;
    private final Caretaker caretaker;

    private UserFacade() {
        this.userController = UserController.getInstance();
        this.caretaker = new Caretaker(); // Inicializa o caretaker
    }

    public static synchronized UserFacade getInstance(){
        if(instance == null){
            instance = new UserFacade();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        caretaker.push(command); // Armazena o comando no Caretaker após execução
    }

    public void registerUserCollection(User user) {
        userController.registerUserCollection(user);
    }

    public void registerUserDatabase(User user) {
        Command command = new RegisterUserCommand(this.userController, user);
        executeCommand(command);
    }

    public void deleteUserDatabase(User user) {
        Command command = new DeleteUserCommand(this.userController, user);
        executeCommand(command);
    }

    public void deleteUserCollection(User user) {
        this.userController.deleteUserCollection(user);
    }

    public List<User> listUsersCollection() {
        return userController.listUsersCollection();
    }

    public List<User> listUsersDatabase() {
        return userController.listUsersDatabase();
    }

    public User getUserDatabaseByLogin(String login) {
        return this.userController.getUserDatabaseByLogin(login);
    }

    public User getUserCollectionByLogin(String login) {
        return this.userController.getUserCollectionByLogin(login);
    }

    public boolean loginUser(String login, String password) {
        return userController.loginUser(login, password);
    }

    public void undo() {
        caretaker.undo(); // Desfaz o último comando usando o Caretaker
    }
}
