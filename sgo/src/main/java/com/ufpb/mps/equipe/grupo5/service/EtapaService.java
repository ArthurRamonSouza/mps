package com.ufpb.mps.equipe.grupo5.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.Etapa;
import com.ufpb.mps.equipe.grupo5.repository.EtapaRepository;

import jakarta.persistence.EntityManager;

public class EtapaService implements Service<Etapa> {

    private final EtapaRepository etapaRepository;
    private final EntityManager entityManager;

    public EtapaService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.etapaRepository = new EtapaRepository(entityManager);
    }

    @Override
    public void save(Etapa etapa) {
        etapaRepository.save(etapa);
    }

    @Override
    public Optional<List<Etapa>> findAll() {
        return etapaRepository.findAll();
    }

    public Optional<Etapa> findById(Long id) {
        return etapaRepository.findById(id);
    }

    @Override
    public void update(Etapa etapa) {
        etapaRepository.update(etapa);
    }

    @Override
    public void delete(Etapa etapa) {
        etapaRepository.delete(etapa);
    }

    @Override
    public Optional<Etapa> findBy(Object id) {
        return etapaRepository.findById((Long) id);
    }

    @Override
    public boolean login(String login, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
