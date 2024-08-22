package com.ufpb.mps.equipe.grupo5.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ufpb.mps.equipe.grupo5.config.JpaUtil;
import com.ufpb.mps.equipe.grupo5.model.User;

import jakarta.persistence.EntityManager;

public class UserDatabaseService implements Service<User> {

    static List<User> usersRecovered;
    static EntityManager entityManager;
    
    public UserDatabaseService(){
        entityManager = JpaUtil.getEntityManager();
        usersRecovered = new ArrayList<>();
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
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<List<User>> findAll() {
        try {
            usersRecovered = entityManager.createQuery("from User", User.class).getResultList();
            System.out.println(String.format("%d usuários foram recuperados da coleção.", usersRecovered.size()));
            
        } catch (Exception e) {
            System.out.println("Erro ao tentar persistir um usuário no banco.");
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return Optional.ofNullable(usersRecovered);

        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(usersRecovered);
        
    }
}
