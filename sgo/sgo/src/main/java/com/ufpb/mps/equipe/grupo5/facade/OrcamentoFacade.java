package com.ufpb.mps.equipe.grupo5.facade;

import java.util.List;
import java.util.Stack;

import com.ufpb.mps.equipe.grupo5.comand.Command;
import com.ufpb.mps.equipe.grupo5.comand.DeleteOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.comand.RegisterOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.comand.UpdateOrcamentoCommand;
import com.ufpb.mps.equipe.grupo5.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class OrcamentoFacade {
    private static OrcamentoFacade instance;
    private final OrcamentoController orcamentoController;
    private static Stack<Command> commandHistory = new Stack<>();

    private OrcamentoFacade() {
        this.orcamentoController = OrcamentoController.getInstance();
    }

    public static synchronized OrcamentoFacade getInstance(){
        if(instance == null){
            instance = new OrcamentoFacade();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    public void registerOrcamento(Orcamento orcamento) {
        Command command = new RegisterOrcamentoCommand(this.orcamentoController, orcamento);
        executeCommand(command);
        commandHistory.push(command);
    }

    public List<Orcamento> listOrcamentos() {
        return orcamentoController.listOrcamentos();
    }

    public void updateOrcamento(Orcamento orcamento) {
        Command command = new UpdateOrcamentoCommand(this.orcamentoController, orcamento);
        commandHistory.push(command);
    }

    public void deleteOrcamento(Orcamento orcamento) {
        Command command = new DeleteOrcamentoCommand(this.orcamentoController, orcamento);
        executeCommand(command);
        commandHistory.push(command);
    }

    public Orcamento findOrcamentoById(Long id) {
        return orcamentoController.findOrcamentoById(id);
    }

    public void undo() {
        Command lastCommand = commandHistory.pop();
        lastCommand.undo();
    }    
}