package com.ufpb.mps.equipe.grupo5.business.data.repository;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.Cotacao;

import jakarta.persistence.EntityManager;

public class CotacaoRepository {

    private final EntityManager entityManager;

    public CotacaoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Cotacao cotacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cotacao);
            entityManager.getTransaction().commit();
            System.out.println("Cotação salva com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar persistir uma cotação no banco.");
        }
    }

    public Optional<List<Cotacao>> findAll() {
        List<Cotacao> cotacoesRecovered;
        try {
            cotacoesRecovered = entityManager.createQuery("from Cotacao", Cotacao.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar cotações no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(cotacoesRecovered);
    }

    public Optional<Cotacao> findById(Long id) {
        Cotacao cotacaoRecovered;
        try {
            cotacaoRecovered = entityManager.find(Cotacao.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar recuperar a cotação pelo ID no banco.");
            return Optional.empty();
        }

        return Optional.ofNullable(cotacaoRecovered);
    }

    public void update(Cotacao cotacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(cotacao); // Atualiza a entidade existente
            entityManager.getTransaction().commit();
            System.out.println("Cotação atualizada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar atualizar a cotação no banco.");
        }
    }

    public void delete(Cotacao cotacao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(cotacao) ? cotacao : entityManager.merge(cotacao));
            entityManager.getTransaction().commit();
            System.out.println("Cotação deletada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar deletar uma cotação no banco.");
        }
    }
}
