package com.ufpb.mps.equipe.grupo5.repository;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.Etapa;

import jakarta.persistence.EntityManager;

public class EtapaRepository {

    private final EntityManager entityManager;

    public EtapaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Etapa etapa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(etapa);
            entityManager.getTransaction().commit();
            System.out.println("Etapa salva com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar persistir uma etapa no banco.");
        }
    }

    public Optional<List<Etapa>> findAll() {
        List<Etapa> etapasRecovered;
        try {
            etapasRecovered = entityManager.createQuery("from Etapa", Etapa.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar etapas no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(etapasRecovered);
    }

    public Optional<Etapa> findById(Long id) {
        Etapa etapaRecovered;
        try {
            etapaRecovered = entityManager.find(Etapa.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar recuperar a etapa pelo ID no banco.");
            return Optional.empty();
        }

        return Optional.ofNullable(etapaRecovered);
    }

    public void update(Etapa etapa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(etapa); // Atualiza a entidade existente
            entityManager.getTransaction().commit();
            System.out.println("Etapa atualizada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar atualizar a etapa no banco.");
        }
    }

    public void delete(Etapa etapa) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(etapa) ? etapa : entityManager.merge(etapa));
            entityManager.getTransaction().commit();
            System.out.println("Etapa deletada com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar deletar uma etapa no banco.");
        }
    }
}
