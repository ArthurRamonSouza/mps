package com.ufpb.mps.equipe.grupo5.business.facade;

import java.util.List;

import com.ufpb.mps.equipe.grupo5.business.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.business.memento.Caretaker;
import com.ufpb.mps.equipe.grupo5.comand.Command;
import com.ufpb.mps.equipe.grupo5.comand.DeleteOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.comand.RegisterOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.comand.UpdateOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoFacade {
    private static OrcamentoFacade instance;
    private final OrcamentoController orcamentoController;
    private final Caretaker caretaker;  // Instância do Caretaker para gerenciar o histórico

    private OrcamentoFacade() {
        this.orcamentoController = OrcamentoController.getInstance();
        this.caretaker = new Caretaker(); // Inicializa o caretaker
    }

    public static synchronized OrcamentoFacade getInstance() {
        if (instance == null) {
            instance = new OrcamentoFacade();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        caretaker.push(command); // Armazena o comando no Caretaker após execução
    }

    public void registerOrcamento(Orcamento orcamento) {
        Command command = new RegisterOrcamentoCommand(this.orcamentoController, orcamento);
        executeCommand(command);
    }

    public List<Orcamento> listOrcamentos() {
        return orcamentoController.listOrcamentos();
    }

    public void updateOrcamento(Orcamento orcamento) {
        Command command = new UpdateOrcamentoCommand(this.orcamentoController, orcamento);
        executeCommand(command);
    }

    public void deleteOrcamento(Orcamento orcamento) {
        Command command = new DeleteOrcamentoCommand(this.orcamentoController, orcamento);
        executeCommand(command);
    }

    public Orcamento findOrcamentoById(Long id) {
        return orcamentoController.findOrcamentoById(id);
    }

    public void undo() {
        caretaker.undo(); // Desfaz a última operação usando o Caretaker
    }
}
