package com.ufpb.mps.equipe.grupo5.controller;

import java.util.ArrayList;
import java.util.List;

import com.ufpb.mps.equipe.grupo5.data.service.Service;
import com.ufpb.mps.equipe.grupo5.factory.InsumoServiceFactory;
import com.ufpb.mps.equipe.grupo5.model.Insumo;
import com.ufpb.mps.equipe.grupo5.util.template.InsumoValidator;

public class InsumoController {

    private static InsumoController instance;
    private Service<Insumo> insumoService;

    public static synchronized InsumoController getInstance() {
        if (instance == null) {
            instance = new InsumoController();
        }
        return instance;
    }

    public void registerInsumoDatabase(Insumo insumo) {
        try {
            InsumoValidator.validateInsumo(insumo);

            this.insumoService = InsumoServiceFactory.createRepository("database");
            this.insumoService.save(insumo);

            System.out.println("Insumo registrado com sucesso no banco de dados.");
        } catch (Exception e) {
            System.err.println("Erro ao registrar insumo: " + e.getMessage());
        }
    }

    public List<Insumo> listInsumos() {
        this.insumoService = InsumoServiceFactory.createRepository("database");
            
        if (this.insumoService.findAll().isPresent())
            return this.insumoService.findAll().get();

        return new ArrayList<Insumo>();
    }

    public void updateInsumo(Insumo insumo) {
        try {
            InsumoValidator.validateInsumo(insumo);

            this.insumoService = InsumoServiceFactory.createRepository("database");
            this.insumoService.update(insumo);

            System.out.println("Insumo atualizado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao atualizar insumo: " + e.getMessage());
        }
    }

    public void deleteInsumoDatabase(Insumo insumo) {
        try {
            this.insumoService = InsumoServiceFactory.createRepository("database");
            this.insumoService.delete(insumo);

            System.out.println("Insumo deletado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao deletar insumo: " + e.getMessage());
        }
    }

    public Insumo findInsumoById(Long id) {
        this.insumoService = InsumoServiceFactory.createRepository("database");
        return this.insumoService.findBy(id).orElse(null);
    }
}
