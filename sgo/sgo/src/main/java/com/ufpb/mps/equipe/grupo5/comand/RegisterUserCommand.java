package com.ufpb.mps.equipe.grupo5.comand;

import com.ufpb.mps.equipe.grupo5.controller.UserController;
import com.ufpb.mps.equipe.grupo5.memento.UserMemento;
import com.ufpb.mps.equipe.grupo5.model.User;

public class RegisterUserCommand implements Command {
    private UserController controller;
    private User user;
    private UserMemento memento;

    public RegisterUserCommand(UserController controller, User user) {
        this.controller = controller;
        this.user = user;
        this.memento = new UserMemento(user);
    }

    @Override
    public void execute() {
        controller.registerUserDatabase(user);
    }

    @Override
    public void undo() {
        if (memento != null) {
            controller.deleteUserDatabase(memento.getState());
            System.out.println("Registro de usuário desfeito e estado anterior restaurado.");
        } else {
            System.out.println("Nenhum estado anterior encontrado para desfazer.");
        }
    }

    @Override
    public Command copy() {
        return new RegisterUserCommand(this.controller, this.user);
    }
}
