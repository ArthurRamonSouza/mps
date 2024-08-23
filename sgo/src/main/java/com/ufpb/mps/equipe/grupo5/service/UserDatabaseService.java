package com.ufpb.mps.equipe.grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.User;

import jakarta.persistence.EntityManager;

public class UserDatabaseService implements Service<User> {

    private final EntityManager entityManager;
    private List<User> usersRecovered;
    
    public UserDatabaseService(){
        this.entityManager = JpaUtil.getEntityManager();
        this.usersRecovered = new ArrayList<>();
    }

    @Override
    public void save(User user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            System.out.println("Usuário salvo com sucesso.");
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Erro ao tentar persistir um usuário no banco.");
        }
    }

    @Override
    public Optional<List<User>> findAll() {
        try {
            usersRecovered = entityManager.createQuery("from User", User.class).getResultList();            
        
        } catch (Exception e) {
            System.out.println("Erro ao tentar listar um usuário no banco.");
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return Optional.ofNullable(usersRecovered);
        }
        return Optional.ofNullable(usersRecovered);
        
    }

    public void disconnect() {
        entityManager.close();
    }
}
