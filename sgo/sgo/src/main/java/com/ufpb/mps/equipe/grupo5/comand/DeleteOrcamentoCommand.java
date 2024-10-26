package com.ufpb.mps.equipe.grupo5.comand;

import com.ufpb.mps.equipe.grupo5.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.memento.OrcamentoMemento;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class DeleteOrcamentoCommand implements Command{
    private OrcamentoController controller;
    private Orcamento orcamento;
    private OrcamentoMemento memento;

    public DeleteOrcamentoCommand(OrcamentoController controller, Orcamento orcamento) {
        this.controller = controller;
        this.orcamento = orcamento;
        this.memento = new OrcamentoMemento(orcamento);
    }

    @Override
    public void execute() {
        controller.deleteOrcamentoDatabase(orcamento);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.registerOrcamentoDatabase(memento.getState());
            System.out.println("Orçamento restaurado no banco de dados.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new DeleteOrcamentoCommand(this.controller, this.orcamento);
    }
}
