/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

   Cacola il Diamentro da un Grafico
 */

package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.PKException;
import at.spengergasse.spoto.Libreria.VarPunti;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class GrafDiametro extends CMDBase {

    //Variabili di Istanza
    String returnNomePunti;
    VarPunti puntiDati = new VarPunti(terminal , "Raggio");
    String nomeGrafico;

    public GrafDiametro(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        nomeGrafico = super.inputString("Nome grafico: ");
        try {
            System.out.println("Grafico (" + nomeGrafico + ") Radius : " + calcola(nomeGrafico));
        }catch(ExeException e) {
            System.out.println(e);
        }
    }//avvio

    public int calcola(String nomeGrafico) throws ExeException {
        if(!super.controllaPK()){
            throw new PKException(this);
        }
        GrafEscentricita escentricita = new GrafEscentricita(terminal);
        int maxDiametro = Integer.MIN_VALUE;

        //Estrago per prima cosa la escentricita
        try {
            returnNomePunti =  escentricita.calcola(nomeGrafico);
            puntiDati = terminal.getPoolPunti().get(returnNomePunti).clone();
        }catch (ExeException e ){
            System.out.println(e);
        }

        //Calcolo del Raggio (Valore Minio)
        for (Map.Entry<String, Integer> entry : puntiDati.getPuntiDati().entrySet()) {
            if(entry.getValue() > maxDiametro){
                maxDiametro = entry.getValue();
            }
        }

        return maxDiametro;
    }//calcola

    @Override
    public void help() {
        System.out.println("Calcoal il Diametro da un Grafic");
    }//help
}//GraDiametro
