package com.ufpb.mps.equipe.grupo5.comand;

public interface Command {
    void execute();
    void undo();
    Command copy();
}