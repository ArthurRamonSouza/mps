package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;

import com.ufpb.mps.equipe.grupo5.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.model.User;

public class Facade {
    private static Facade instance;
    private final OrcamentoController orcamentoController;
    private final UserController userController;

    private Facade() {
        this.orcamentoController = OrcamentoController.getInstance();
        this.userController = UserController.getInstance();
    }

    public static synchronized Facade getInstance(){
        if(instance == null){
            instance = new Facade();
        }
        return instance;
    }

    // Facade Orcamento 
    public void registerOrcamento(Orcamento orcamento) {
        orcamentoController.registerOrcamento(orcamento);
    }

    public List<Orcamento> listOrcamentos() {
        return orcamentoController.listOrcamentos();
    }

    public void updateOrcamento(Orcamento orcamento) {
        orcamentoController.updateOrcamento(orcamento);
    }

    public void deleteOrcamento(Orcamento orcamento) {
        orcamentoController.deleteOrcamento(orcamento);
    }

    public Orcamento findOrcamentoById(Long id) {
        return orcamentoController.findOrcamentoById(id);
    }

    //Facade user
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