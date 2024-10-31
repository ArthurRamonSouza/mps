package com.ufpb.mps.equipe.grupo5.comand;

import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.infra.memento.UserMemento;
import com.ufpb.mps.equipe.grupo5.model.User;

public class DeleteUserCommand implements Command {
    private UserController controller;
    private User user;
    private UserMemento memento;

    public DeleteUserCommand(UserController controller, User user) {
        this.controller = controller;
        this.user = user;
        this.memento = new UserMemento(user);
    }

    @Override
    public void execute() {
        controller.deleteUserDatabase(user);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.registerUserDatabase(memento.getState());
            System.out.println("Usuário restaurado no banco de dados.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new DeleteUserCommand(this.controller, this.user);
    }
}
