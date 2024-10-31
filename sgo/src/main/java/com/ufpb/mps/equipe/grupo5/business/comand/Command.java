package com.ufpb.mps.equipe.grupo5.business.comand;

public interface Command {
    void execute();
    void undo();
    Command copy();
}