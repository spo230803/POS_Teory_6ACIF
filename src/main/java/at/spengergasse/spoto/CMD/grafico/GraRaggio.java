package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.exception.PKException;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class GraRaggio extends CMDBase {

    //Variabili di Instanza
    String returnNomePunti;
    VarPunti puntiDati = new VarPunti(terminal , "Raggio");
    String nomeGrafico;

    public GraRaggio(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        nomeGrafico = super.inputString("Nome grafico: ");

        try {
            System.out.println("Grafico ("+nomeGrafico+") Radius : "+calcola(nomeGrafico));
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }

    }//avvio

    public int calcola(String nomeGrafico) throws ExeException {
        if(!super.controllaPK()){ throw  new PKException(this);}
        GrafEscentricita escentricita = new GrafEscentricita(terminal);
        int minRadio = Integer.MAX_VALUE;

        //Estrago per prima cosa la escentricita
        try {
            returnNomePunti =  escentricita.calcola(nomeGrafico);
            puntiDati = terminal.getPoolPunti().get(returnNomePunti).clone();
        }catch (ExeException e ){
            System.out.println(e);
        }

        //Calcolo del Raggio (Valore Minio)
        for (Map.Entry<String, Integer> entry : puntiDati.getPuntiDati().entrySet()) {
            Libreria.debug(entry.getValue());
            if(entry.getValue() < minRadio){
                minRadio = entry.getValue();
            }
        }

        return minRadio;
    }//calcola

    @Override
    public void help() {
        System.out.println("Calcoal il Raggio da un Grafic");
    }//help
}//GraRaggio
