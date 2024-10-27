package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.model.Composicao;
import com.ufpb.mps.equipe.grupo5.service.ComposicaoService;
import com.ufpb.mps.equipe.grupo5.service.Service;

public class ComposicaoServiceFactory {
    
    public static Service<Composicao> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new ComposicaoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
