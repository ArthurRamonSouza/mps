package com.ufpb.mps.equipe.grupo5.repository;

import com.ufpb.mps.equipe.grupo5.model.UserAccessData;
import com.ufpb.mps.equipe.grupo5.config.JpaUtil;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserAccessDataRepository {
    private final EntityManager entityManager;

    public UserAccessDataRepository() {
        this.entityManager = JpaUtil.getEntityManager();
    }

    public void save(UserAccessData userAccessData) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userAccessData);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Optional<List<UserAccessData>> findAll() {
        List<UserAccessData> log;

        try {
            log = entityManager.createQuery("SELECT u FROM UserAccessData u", UserAccessData.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao tentar encontrar os acessos dos usuários no banco.");
            return Optional.empty();
        }
        return Optional.ofNullable(log);
    }

    public Optional<List<UserAccessData>> findAllByUserName(String userName) {
        List<UserAccessData> log;
        try {

            log = entityManager.createQuery("SELECT u FROM UserAccessData u WHERE u.userName = :userName", UserAccessData.class)
            .setParameter("userName", userName)
            .getResultList();
        } catch (Exception e) {
            System.out.printf("Erro ao tentar encontrar os acessos do usuário %s no banco.", userName);
            return Optional.empty();
        }
    
        return Optional.ofNullable(log);
    }

    public Optional<UserAccessData> findById(Long id) {
        UserAccessData log;
        try {
            log = entityManager.createQuery("SELECT u FROM UserAccessData u WHERE u.id = :id", UserAccessData.class)
            .setParameter("id", id)
            .getResultList().get(0);

        } catch (Exception e) {
            System.out.printf("Erro ao tentar encontrar os acessos do usuário %d no banco.", id);
            return Optional.empty();
        }
    
        return Optional.ofNullable(log);
    }
    

    public void update(UserAccessData userAccessData) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(userAccessData);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(UserAccessData userAccessData) {
        try {
            entityManager.getTransaction().begin();
            UserAccessData managedUserAccessData = entityManager.find(UserAccessData.class, userAccessData.getUserName());
            if (managedUserAccessData != null) {
                entityManager.remove(managedUserAccessData);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }
}
