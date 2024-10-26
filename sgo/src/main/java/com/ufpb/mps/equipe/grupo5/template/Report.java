package com.ufpb.mps.equipe.grupo5.template;

public abstract class Report {
    public final void generateReport() {
        fetchData();
        processData();
        printReport();
    }

    protected abstract void fetchData();
    protected abstract void processData();
    protected abstract void printReport();
}

