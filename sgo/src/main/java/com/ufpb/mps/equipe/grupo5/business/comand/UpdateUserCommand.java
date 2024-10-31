package com.ufpb.mps.equipe.grupo5.business.comand;

import com.ufpb.mps.equipe.grupo5.business.controller.UserController;
import com.ufpb.mps.equipe.grupo5.business.memento.UserMemento;
import com.ufpb.mps.equipe.grupo5.model.User;

public class UpdateUserCommand implements Command {
     private UserController controller;
    private User user;
    private UserMemento memento;

    public UpdateUserCommand(UserController controller, User user) {
        this.controller = controller;
        this.user = user;
        this.memento = new UserMemento(user);
    }

    @Override
    public void execute() {
        controller.updateUserData(user);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.updateUserData(memento.getState());
            System.out.println("Usuário restaurado no banco de dados.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new UpdateUserCommand(this.controller, this.user);
    }
    
}
