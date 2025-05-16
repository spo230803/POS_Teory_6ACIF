/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Calcolo Escentricita  da un Grafico
 */
package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.Terminale;

import java.util.*;

public class GrafEscentricita extends CMDBase {

    //Variabili in Istanza
    VarGrafico grafico;
    Map<String, Map<String, Integer>>  graficoDati;
    VarPunti returnPunti;
    String nomeGrafico;
    String nomeReturn = terminal.getNomeRisultato();

    public GrafEscentricita(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        nomeGrafico = super.inputString("Nome grafico per il calcolo delle Distanze");
        try{
            calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
        }
        System.out.println("Escentricita calcolata Corettamente e salvata in "+nomeReturn+"");
        System.out.println(returnPunti);
    }//avvio

    public String calcola(String parNomeGrafico) throws ExeException {
        //Funzone per il calcolo della Escertica
        //      PARAMETRI
        // parNomeGrafico   Nome del grafico da calcolare la Matrice
        //
        //      RETRUN
        // nomeMatriceReturn = Nome della matrice Creata che è stata salvata nel Pool nel Terminale (Oggetto)
        nomeGrafico = parNomeGrafico;

        if(!super.controllaPK()){throw new PKException(this);}


        //Carico il Grafico
        try {
            grafico =  terminal.getPoolGrafico().get(nomeGrafico).clone();
            returnPunti = new VarPunti(terminal , grafico , "Escentricita");
            Libreria.debug(grafico);
        } catch (Exception e) {
            ExeException errore = new ExeException(this , "ricerca Grafico","Errore nel caricamento grafico ("+nomeGrafico+") : "+ e.getMessage() );
            throw errore;
        }

        //Carico in locale il Grafico
        graficoDati = grafico.getGraficoDati();
        Set<String> elencoPunti = graficoDati.keySet(); // tutti i nodi

        //Cicollo per Punti e calcolo il massimo
        for(String puntoID : elencoPunti){
            Map<String, Integer> punti = graficoDati.get(puntoID);
            int maxTrovato = Integer.MIN_VALUE;
            for(String puntoOra : punti.keySet()){
                Libreria.debug(puntoOra);
                Libreria.debug(graficoDati.get(puntoOra).get(puntoID));
                if(graficoDati.get(puntoOra).get(puntoID) > maxTrovato){
                    maxTrovato = graficoDati.get(puntoOra).get(puntoID);
                }
            }//
            returnPunti.addValore(puntoID, maxTrovato);
        }//for - Ciclo per punti

        terminal.addPoolPunit(nomeReturn , returnPunti);
        return nomeReturn;
    }//calcola


    @Override
    public void help() {
        System.out.println("Calcola la Escentricita di un grafico");
    }//help


}//GraEscentricita
