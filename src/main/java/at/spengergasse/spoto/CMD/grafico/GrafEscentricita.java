/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.1
    del     : 2025-05-17

    Calcolo Escentricita  dalla Matrice di un Grafico
 */
package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.exception.PKException;
import at.spengergasse.spoto.Terminale;

import java.util.*;

public class GrafEscentricita extends CMDBase {

    //Variabili in Istanza
    VarGrafico grafico;
    Map<String, Map<String, Integer>>  graficoDati;
    VarPunti returnPunti;
    String nomeGrafico;
    String nomeReturn = terminal.getNomeRisultato();
    VarMatrice matrice;

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
            return;
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
        // nomeMatriceReturn = Nome VarPunti che è stata salvata nel Pool nel Terminale (Oggetto)
        nomeGrafico = parNomeGrafico;
        GrafCaclolaDistanza matDaGrafico = new GrafCaclolaDistanza(terminal);
        String retunrMatrice;
        VarMatrice matrice = new VarMatrice();


        if(!super.controllaPK()){throw new PKException(this);}

        try{
            super.controllaEsisteGrafico(parNomeGrafico);
        }catch(ExeException e){
            throw e;
        }

        //Carico il Grafico
        try {
            grafico =  terminal.getPoolGrafico().get(nomeGrafico).clone(); //Se non trovat va in erroe
            String retunMatrice = matDaGrafico.calcola(nomeGrafico);
            returnPunti = new VarPunti(terminal , grafico , "Escentricita");
            matrice = terminal.getPoolMatrici().get(retunMatrice);
            Libreria.debug(grafico);
        } catch (Exception e) {
            ExeException errore = new ExeException(this , "ricerca Grafico","Errore nel caricamento grafico ("+nomeGrafico+") : "+ e.getMessage() );
            throw errore;
        }

        //Carico in locale il Grafico
        graficoDati = grafico.getGraficoDati();
        Set<String> elencoPunti = graficoDati.keySet(); // tutti i nodi

/* -- OLD ciclo sul grafico
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
            }//putni ora
            returnPunti.addValore(puntoID, maxTrovato);
        }//for - Ciclo per punti

*/

        //Cicollo sulla Matice per Punti e calcolo il massimo
        ArrayList<ArrayList<Integer>> matriceDati = matrice.getMatriceDati();
        for(int i = 0; i < matriceDati.size(); i++){
            int maxTrovato = Integer.MIN_VALUE;
            for(int j = 0; j < matriceDati.get(i).size(); j++){
                if(matriceDati.get(i).get(j) > maxTrovato){
                    maxTrovato = matriceDati.get(i).get(j);
                }//end IF
            }//end Punto Ora
            returnPunti.addValore(matrice.getNomePuntoDaIndex(i), maxTrovato);
        }//end Cicolo Punti

        //Salvataggio dati Calcolati
        terminal.addPoolPunit(nomeReturn , returnPunti.clone());
        return nomeReturn;
    }//calcola


    @Override
    public void help() {
        System.out.println("Calcola la Escentricita da una Matrice partendo da un Grafico");
    }//help


}//GraEscentricita
