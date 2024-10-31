package com.ufpb.mps.equipe.grupo5.business.comand;

import com.ufpb.mps.equipe.grupo5.business.controller.OrcamentoController;
import com.ufpb.mps.equipe.grupo5.business.memento.OrcamentoMemento;
import com.ufpb.mps.equipe.grupo5.model.Orcamento;

public class RegisterOrcamentoCommand implements Command {
    private OrcamentoController controller;
    private Orcamento orcamento;
    private OrcamentoMemento memento;

    public RegisterOrcamentoCommand(OrcamentoController controller, Orcamento orcamento) {
        this.controller = controller;
        this.orcamento = orcamento;
        this.memento = new OrcamentoMemento(orcamento);
    }

    @Override
    public void execute() {
        controller.registerOrcamentoDatabase(orcamento);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.deleteOrcamentoDatabase(memento.getState());
            System.out.println("Registro de orçamento desfeito e estado anterior restaurado.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new RegisterOrcamentoCommand(this.controller, this.orcamento);
    }
}
