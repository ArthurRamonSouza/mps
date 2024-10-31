package com.ufpb.mps.equipe.grupo5.business.data.repository;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.Composicao;

import jakarta.persistence.EntityManager;

public class ComposicaoRepository {

    private final EntityManager entityManager;

    public ComposicaoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Composicao composicao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(composicao);
            entityManager.getTransaction().commit();
            System.out.println("Composição salva com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar persistir uma composição no banco.");
        }
    }

    public Optional<List<Composicao>> findAll() {
        List<Composicao> composicoesRecovered;
        try {
            composicoesRecovered = entityManager.createQuery("from Composicao", Composicao.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar composições no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(composicoesRecovered);
    }

    public Optional<Composicao> findById(Long id) {
        Composicao composicaoRecovered;
        try {
            composicaoRecovered = entityManager.find(Composicao.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar recuperar a composição pelo ID no banco.");
            return Optional.empty();
        }

        return Optional.ofNullable(composicaoRecovered);
    }

    public void update(Composicao composicao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(composicao); // Atualiza a entidade existente
            entityManager.getTransaction().commit();
            System.out.println("Composição atualizada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar atualizar a composição no banco.");
        }
    }

    public void delete(Composicao composicao) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(composicao) ? composicao : entityManager.merge(composicao));
            entityManager.getTransaction().commit();
            System.out.println("Composição deletada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar deletar uma composição no banco.");
        }
    }
}
