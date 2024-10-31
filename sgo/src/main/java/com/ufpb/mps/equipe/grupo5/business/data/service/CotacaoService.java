package com.ufpb.mps.equipe.grupo5.business.data.service;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.Cotacao;
import com.ufpb.mps.equipe.grupo5.repository.CotacaoRepository;

import jakarta.persistence.EntityManager;

public class CotacaoService implements Service<Cotacao> {

    private final CotacaoRepository cotacaoRepository;
    private final EntityManager entityManager;

    public CotacaoService() {
        this.entityManager = JpaUtil.getEntityManager();
        this.cotacaoRepository = new CotacaoRepository(entityManager);
    }

    @Override
    public void save(Cotacao cotacao) {
        cotacaoRepository.save(cotacao);
    }

    @Override
    public Optional<List<Cotacao>> findAll() {
        return cotacaoRepository.findAll();
    }

    public Optional<Cotacao> findById(Long id) {
        return cotacaoRepository.findById(id);
    }

    @Override
    public void update(Cotacao cotacao) {
        cotacaoRepository.update(cotacao);
    }

    @Override
    public void delete(Cotacao cotacao) {
        cotacaoRepository.delete(cotacao);
    }

    @Override
    public Optional<Cotacao> findBy(Object id) {
        return cotacaoRepository.findById((Long) id);
    }

    @Override
    public boolean login(String login, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
