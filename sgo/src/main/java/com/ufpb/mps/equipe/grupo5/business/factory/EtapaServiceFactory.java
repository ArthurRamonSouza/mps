package com.ufpb.mps.equipe.grupo5.business.factory;

import com.ufpb.mps.equipe.grupo5.business.data.service.EtapaService;
import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Etapa;

public class EtapaServiceFactory {
    
    public static Service<Etapa> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new EtapaService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
