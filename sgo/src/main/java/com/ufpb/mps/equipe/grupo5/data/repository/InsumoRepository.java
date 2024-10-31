package com.ufpb.mps.equipe.grupo5.repository;

import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.model.Insumo;

import jakarta.persistence.EntityManager;

public class InsumoRepository {

    private final EntityManager entityManager;

    public InsumoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Insumo insumo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(insumo);
            entityManager.getTransaction().commit();
            System.out.println("Insumo salvo com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar persistir um insumo no banco.");
        }
    }

    public Optional<List<Insumo>> findAll() {
        List<Insumo> insumosRecovered;
        try {
            insumosRecovered = entityManager.createQuery("from Insumo", Insumo.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar insumos no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(insumosRecovered);
    }

    public Optional<Insumo> findById(Long id) {
        Insumo insumoRecovered;
        try {
            insumoRecovered = entityManager.find(Insumo.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao tentar recuperar o insumo pelo ID no banco.");
            return Optional.empty();
        }

        return Optional.ofNullable(insumoRecovered);
    }

    public void update(Insumo insumo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(insumo); // Atualiza a entidade existente
            entityManager.getTransaction().commit();
            System.out.println("Insumo atualizado com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar atualizar o insumo no banco.");
        }
    }

    public void delete(Insumo insumo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.contains(insumo) ? insumo : entityManager.merge(insumo));
            entityManager.getTransaction().commit();
            System.out.println("Insumo deletado com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println("Erro ao tentar deletar um insumo no banco.");
        }
    }
}
