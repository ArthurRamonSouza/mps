package com.ufpb.mps.equipe.grupo5.business.comand;

import com.ufpb.mps.equipe.grupo5.model.Orcamento;
import com.ufpb.mps.equipe.grupo5.business.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.business.memento.OrcamentoMemento;

public class UpdateOrcamentoCommand implements Command {
     private OrcamentoController controller;
    private Orcamento orcamento;
    private OrcamentoMemento memento;

    public UpdateOrcamentoCommand(OrcamentoController controller, Orcamento orcamento) {
        this.controller = controller;
        this.orcamento = orcamento;
        this.memento = new OrcamentoMemento(orcamento);
    }

    @Override
    public void execute() {
        controller.updateOrcamento(orcamento);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.updateOrcamento(memento.getState());
            System.out.println("Orçamento restaurado no banco de dados.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new UpdateOrcamentoCommand(this.controller, this.orcamento);
    }
    
}
