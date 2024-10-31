package com.ufpb.mps.equipe.grupo5.business.data.service;

import com.ufpb.mps.equipe.grupo5.model.UserAccessData;
import com.ufpb.mps.equipe.grupo5.repository.UserAccessDataRepository;

import java.util.List;
import java.util.Optional;

public class UserAccessDataService implements Service<UserAccessData> {
    private final UserAccessDataRepository repository;

    public UserAccessDataService() {
        this.repository = new UserAccessDataRepository();
    }

    @Override
    public void save(UserAccessData entity) {
        repository.save(entity);
    }

    @Override
    public Optional<List<UserAccessData>> findAll() {
        return repository.findAll();
    }

    public Optional<List<UserAccessData>> findAllBy(Object param) {
        return repository.findAllByUserName((String) param);
    }

    @Override
    public void update(UserAccessData entity) {
        repository.update(entity);
    }

    @Override
    public void delete(UserAccessData entity) {
        repository.delete(entity);
    }

    @Override
    public boolean login(String login, String password) {
        // Implementar lógica de login se necessário
        return false;
    }

    @Override
    public Optional<UserAccessData> findBy(Object id) {
        return repository.findById((Long) id);
    }
}
