package com.exercice.threadsDemo.threads;

import ch.qos.logback.core.util.StringUtil;

import java.util.concurrent.Callable;

public class OddNumberThread implements Callable<String> {
    private final Long limit;
    private final int idRecherche;

    public OddNumberThread(Long limit, int idRecherche) {
        this.limit = limit;
        this.idRecherche = idRecherche;
    }

    @Override
    public String call() throws Exception {
        Long nbOdds = 0L;
        for (int i=0;i<limit;i++){
            if (i%2!=0) {
                nbOdds++;
            }
        }
        return String.format("Recherche %s ,nombre de valeur impaire : %d.",idRecherche+1, nbOdds.intValue());
    }
}
