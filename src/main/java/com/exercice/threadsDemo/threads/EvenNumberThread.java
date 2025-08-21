package com.exercice.threadsDemo.threads;

import java.util.concurrent.Callable;

public class EvenNumberThread implements Callable<String> {
    private final Long limit;
    private final int idRecherche;

    public EvenNumberThread(Long limit, int idRecherche) {
        this.limit = limit;
        this.idRecherche = idRecherche;
    }

    @Override
    public String call() throws Exception {
        Long nbEven = 0L;
        for (int i=0;i<limit;i++){
            if (i%2==0) {
                nbEven++;
            }
        }
        return String.format("Recherche %s ,nombre de valeur paire : %d.",idRecherche+1, nbEven.intValue());    }
}
