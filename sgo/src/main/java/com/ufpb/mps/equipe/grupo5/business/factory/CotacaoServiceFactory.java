package com.ufpb.mps.equipe.grupo5.business.factory;

import com.ufpb.mps.equipe.grupo5.business.data.service.CotacaoService;
import com.ufpb.mps.equipe.grupo5.business.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Cotacao;

public class CotacaoServiceFactory {
    
    public static Service<Cotacao> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new CotacaoService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
