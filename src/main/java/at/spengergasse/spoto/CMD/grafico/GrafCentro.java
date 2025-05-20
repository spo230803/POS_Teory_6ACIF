/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Calcola il Centro dal Grafico
 */

package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.exception.PKException;
import at.spengergasse.spoto.Terminale;

public class GrafCentro extends CMDBase {

    //Variabilio Istanza
    private VarPunti puntiDatiOrigini, puntiDatiReturn;
    private String returnNomeVar = terminal.getNomeRisultato();


    public GrafCentro(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeGrafico = super.inputString("Nome grafico: ");

        try{
            calcola(nomeGrafico);
        }catch(ExeException e){
            System.out.println(e);
            return;
        }

        System.out.println("Cetnro calcolato corettamento e salvato (punti) in : "+returnNomeVar+"");
        VarPunti punti = terminal.getPoolPunti().get(returnNomeVar);
        System.out.println(punti);
    }//avvio

    public String calcola(String nomeGrafico) throws ExeException {
        if(!super.controllaPK()){ throw new PKException(this);}

        //Variabili locale
        try{
            super.controllaEsisteGrafico(nomeGrafico);
        }catch (ExeException e){
            throw e;
        }

        VarGrafico grafio = terminal.getPoolGrafico().get(nomeGrafico);



        GrafEscentricita escentricita = new GrafEscentricita(terminal);
        GraRaggio raggio = new GraRaggio(terminal);
        puntiDatiReturn = new VarPunti(terminal , grafio , "Centro");
        int raggioVal;

        //Calcolo dei Punti e del Raggio
        try{
            String returnPuntiOrigniali = escentricita.calcola(nomeGrafico);
            puntiDatiOrigini = terminal.getPoolPunti().get(returnPuntiOrigniali).clone();
            raggioVal = raggio.calcola(nomeGrafico);
        } catch (ExeException e) {
            throw e;
        }

        for(String puntoOra : puntiDatiOrigini.getPuntiDati().keySet()){
            if(puntiDatiOrigini.getPuntiDati().get(puntoOra) == raggioVal){
                puntiDatiReturn.addValore(puntoOra , raggioVal);
            }
        }//end for

        Libreria.debug(puntiDatiReturn);

        terminal.addPoolPunit(returnNomeVar, puntiDatiReturn.clone());
        return returnNomeVar;
    }//calcola

    @Override
    public void help() {
        System.out.println("Calcola il Centro da un Grafico");
    }//help
}//GrafCentro
