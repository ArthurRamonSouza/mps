package com.ufpb.mps.equipe.grupo5.business.data.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.Composicao;
import com.ufpb.mps.equipe.grupo5.repository.ComposicaoRepository;

import jakarta.persistence.EntityManager;

public class ComposicaoService implements Service<Composicao> {

    private final ComposicaoRepository composicaoRepository;
    private final EntityManager entityManager;

    public ComposicaoService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.composicaoRepository = new ComposicaoRepository(entityManager);
    }

    @Override
    public void save(Composicao composicao) {
        composicaoRepository.save(composicao);
    }

    @Override
    public Optional<List<Composicao>> findAll() {
        return composicaoRepository.findAll();
    }

    public Optional<Composicao> findById(Long id) {
        return composicaoRepository.findById(id);
    }

    @Override
    public void update(Composicao composicao) {
        composicaoRepository.update(composicao);
    }

    @Override
    public void delete(Composicao composicao) {
        composicaoRepository.delete(composicao);
    }

    @Override
    public Optional<Composicao> findBy(Object id) {
        return composicaoRepository.findById((Long) id);
    }

    @Override
    public boolean login(String login, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
