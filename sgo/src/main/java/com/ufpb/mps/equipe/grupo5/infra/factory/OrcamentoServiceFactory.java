package com.ufpb.mps.equipe.grupo5.factory;

import com.ufpb.mps.equipe.grupo5.data.service.OrcamentoDatabaseService;
import com.ufpb.mps.equipe.grupo5.data.service.Service;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoServiceFactory  {
    public static Service<Orcamento> createRepository(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return new OrcamentoDatabaseService();
        }
        throw new IllegalArgumentException("Unknown repository type");
    }
}
