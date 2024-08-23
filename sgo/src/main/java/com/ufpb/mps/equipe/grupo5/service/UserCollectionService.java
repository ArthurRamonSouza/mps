<<<<<<< HEAD
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
        System.out.println(String.format("%d usuários foram recuperados da coleção.", userArray.size()));
        return Optional.ofNullable(userArray);
    }
=======
package com.ufpb.mps.equipe.grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.User;

public class UserCollectionService implements Service<User> {

    private static List<User> userArray;

    public UserCollectionService(){
        userArray = new ArrayList<User>();
    }

    @Override
    public void save(User user) {
        userArray.add(user);
        System.out.println(String.format("Usuário %s persistido na coleção.", user.getName()));
    }

    @Override
    public Optional<List<User>> findAll() {
        System.out.println(String.format("%d usuários foram recuperados da coleção.", userArray.size()));
        return Optional.ofNullable(userArray);
    }
>>>>>>> ea612e88543f0ab371c29e79412e3e85ba760912
}