package com.ufpb.mps.equipe.grupo5.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.repository.UserRepository;

import jakarta.persistence.EntityManager;

public class UserDatabaseService implements Service<User> {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

    public UserDatabaseService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.userRepository = new UserRepository(entityManager);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<List<User>> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findBy(Object login) {
        return userRepository.findByLogin((String) login);
    }

    @Override
    public void update(User updatedUser) {
        userRepository.update(updatedUser);
    }

    @Override
    public void delete(User userToDelete) {
        userRepository.delete(userToDelete.getCpf());
    }

    public boolean login(String login, String password) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
