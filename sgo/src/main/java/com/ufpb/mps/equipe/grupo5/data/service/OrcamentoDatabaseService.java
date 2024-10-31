package com.ufpb.mps.equipe.grupo5.data.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.repository.OrcamentoRepository;

import jakarta.persistence.EntityManager;

public class OrcamentoDatabaseService implements Service<Orcamento> {

    private final OrcamentoRepository orcamentoRepository;
    private final EntityManager entityManager;

    public OrcamentoDatabaseService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.orcamentoRepository = new OrcamentoRepository(entityManager);
    }

    @Override
    public void save(Orcamento orcamento) {
        orcamentoRepository.save(orcamento);
    }

    @Override
    public Optional<List<Orcamento>> findAll() {
        return orcamentoRepository.findAll();
    }

    public Optional<Orcamento> findById(Long id) {
        return orcamentoRepository.findById(id);
    }

    @Override
    public void update(Orcamento orcamento) {
        orcamentoRepository.update(orcamento);
    }

    @Override
    public void delete(Orcamento orcamento) {
        orcamentoRepository.delete(orcamento);
    }

    @Override
    public Optional<Orcamento> findBy(Object id) {
       return orcamentoRepository.findById((Long) id);
    }

    @Override
    public boolean login(String login, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
