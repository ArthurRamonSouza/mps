package com.ufpb.mps.equipe.grupo5.memento;
import java.util.Stack;

import com.ufpb.mps.equipe.grupo5.comand.Command;

public class Caretaker {
    private Stack<Command> commandStack = new Stack<>();

    public void push(Command command) {
        this.commandStack.push(command);
    }

    public Command viewStackTop() {
        if (!this.commandStack.isEmpty()) {
            System.out.println(this.commandStack.firstElement());
            return this.commandStack.firstElement();
        }
        
        return null;
    }
    public void undo() {
        if (!this.commandStack.isEmpty()) {
            this.commandStack.firstElement().undo();
            this.commandStack.pop();
        }
    }
}
