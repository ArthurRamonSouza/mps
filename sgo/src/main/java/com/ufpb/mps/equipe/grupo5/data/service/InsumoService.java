package com.ufpb.mps.equipe.grupo5.data.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.Insumo;
import com.ufpb.mps.equipe.grupo5.repository.InsumoRepository;

import jakarta.persistence.EntityManager;

public class InsumoService implements Service<Insumo> {

    private final InsumoRepository insumoRepository;
    private final EntityManager entityManager;

    public InsumoService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.insumoRepository = new InsumoRepository(entityManager);
    }

    @Override
    public void save(Insumo insumo) {
        insumoRepository.save(insumo);
    }

    @Override
    public Optional<List<Insumo>> findAll() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> findById(Long id) {
        return insumoRepository.findById(id);
    }

    @Override
    public void update(Insumo insumo) {
        insumoRepository.update(insumo);
    }

    @Override
    public void delete(Insumo insumo) {
        insumoRepository.delete(insumo);
    }

    @Override
    public Optional<Insumo> findBy(Object id) {
        return insumoRepository.findById((Long) id);
    }

    @Override
    public boolean login(String login, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
