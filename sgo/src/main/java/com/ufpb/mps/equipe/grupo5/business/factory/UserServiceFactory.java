package com.ufpb.mps.equipe.grupo5.business.factory;

import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.business.data.service.UserCollectionService;
import com.ufpb.mps.equipe.grupo5.business.data.service.UserDatabaseService;
import com.ufpb.mps.equipe.grupo5.model.User;

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
