package com.ufpb.mps.equipe.grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.User;

public class UserCollectionService implements Service<User> {

    private List<User> userArray;

    public UserCollectionService(){
        this.userArray = new ArrayList<User>();
    }

    @Override
    public void save(User user) {
        userArray.add(user);
        System.out.println(String.format("Usuário %s persistido na coleção.", user.getName()));
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.ofNullable(userArray);
    }
}