package com.ufpb.mps.equipe.grupo5.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.repository.UserRepository;

import jakarta.persistence.EntityManager;

public class UserDatabaseService implements Service<User> {

    private final UserRepository userRepository;

    public UserDatabaseService() {
        EntityManager entityManager = JpaUtil.getEntityManager();
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
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public boolean login(String login, String password) {
        Optional<User> userOpt = this.findByLogin(login);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
