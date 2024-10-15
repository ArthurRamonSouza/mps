package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.model.User;
import com.ufpb.mps.equipe.grupo5.service.Service;
import com.ufpb.mps.equipe.grupo5.service.UserCollectionService;
import com.ufpb.mps.equipe.grupo5.service.UserDatabaseService;

public class UserServiceFactory  {
    public static Service<User> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new UserDatabaseService();
        } else if ("collection".equalsIgnoreCase(type)) {
            return new UserCollectionService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
