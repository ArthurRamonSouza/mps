package com.ufpb.mps.equipe.grupo5.repository;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.Orcamento;

import jakarta.persistence.EntityManager;

public class OrcamentoRepository {

    private final EntityManager entityManager;

    public OrcamentoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Orcamento orcamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(orcamento);
            entityManager.getTransaction().commit();
            System.out.println("Orçamento salvo com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar persistir um orçamento no banco.");
        }
    }

    public Optional<List<Orcamento>> findAll() {
        List<Orcamento> orcamentosRecovered;
        try {
            orcamentosRecovered = entityManager.createQuery("from Orcamento", Orcamento.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar orçamentos no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(orcamentosRecovered);
    }

    public Optional<Orcamento> findById(Long id) {
        Orcamento orcamentoRecovered;
        try {
            orcamentoRecovered = entityManager.find(Orcamento.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar recuperar o orçamento pelo ID no banco.");
            return Optional.empty();
        }

        return Optional.ofNullable(orcamentoRecovered);
    }

    public void update(Orcamento orcamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(orcamento); // Atualiza a entidade existente
            entityManager.getTransaction().commit();
            System.out.println("Orçamento atualizado com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar atualizar o orçamento no banco.");
        }
    }

    public void delete(Orcamento orcamento) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(orcamento) ? orcamento : entityManager.merge(orcamento));
            entityManager.getTransaction().commit();
            System.out.println("Orçamento deletado com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar deletar um orçamento no banco.");
        }
    }
}
