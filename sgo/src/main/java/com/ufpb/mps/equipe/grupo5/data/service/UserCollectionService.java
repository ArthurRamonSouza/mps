package com.ufpb.mps.equipe.grupo5.data.service;

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

    @Override
    public Optional<User> findBy(Object login) {
        for (User user : userArray) {
            if(user.getLogin().equals((String) login))
                return Optional.of(user);
        }

        return Optional.empty(); 
    }

    @Override
    public void update(User updatedUser) {
        for (int i = 0; i < userArray.size(); i++) {
            User user = userArray.get(i);
            if (user.getLogin().equals(updatedUser.getLogin())) {
                userArray.set(i, updatedUser);  // Substitui o usuário existente pelo atualizado
                System.out.println(String.format("Usuário %s atualizado na coleção.", updatedUser.getName()));
                return;
            }
        }
        System.out.println("Usuário não encontrado para atualização.");
    }

    @Override
    public void delete(User userToDelete) {
        Optional<User> userOptional = findBy((String) userToDelete.getLogin());
        if (userOptional.isPresent()) {
            userArray.remove(userOptional.get());
            System.out.println(String.format("Usuário %s removido da coleção.", userToDelete.getName()));
        } else {
            System.out.println("Usuário não encontrado para remoção.");
        }
    }

    @Override
    public boolean login(String login, String password) {
        for (int i = 0; i < userArray.size(); i++) {
            User user = userArray.get(i);
            if (user.getLogin().equals(login)) {
                if (user.getPassword().equals(password))
                return true;
            }
        }
        return false;
    }
}
