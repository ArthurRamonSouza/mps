package com.ufpb.mps.equipe.grupo5.service;

import java.util.List;
import java.util.Optional;

public interface Service<T> {
    
    public void save(T entity);

    public Optional<List<T>> findAll();

    public Optional<T> findByLogin(String login);

}
