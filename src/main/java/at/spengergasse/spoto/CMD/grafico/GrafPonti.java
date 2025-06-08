/*
    Autore  : SPOTO Giorgio Matteo
    Classe  : 6A CIF
    Ver     : 1.1.0
    del     : 2025-06-07

    Classe per la riceca dei Ponti
*/

package at.spengergasse.spoto.CMD.grafico;
import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import java.util.*;

import static at.spengergasse.spoto.Main.SPS_MAX_LOOP;

public class GrafPonti extends CMDBase {

    //Variabili Istanza
    private List<String> returnPonti = new ArrayList<>();
    private Set<String> puntiVisitati = new HashSet<>();
    private Map<String, Integer> tin = new HashMap<>();
    private Map<String, Integer> low = new HashMap<>();
    private int tempo = 0;
    private int sps = 0;


    public GrafPonti(Terminale terminal) {
        super(terminal);
    }//Construttor

    @Override
    public void avvio() {
        String nomeGrafico = super.inputString("Nome Grafico");

        try{
            calcola(nomeGrafico);
            System.out.println(printReturn());
        }catch (ExeException e){
            System.out.println(e);
        }

    }//avvio

    public List<String> calcola(String nomeGrafico) throws ExeException {
        VarGrafico grafico;
        try{
            super.controllaEsisteGrafico(nomeGrafico);
            grafico = terminal.getPoolGrafico().get(nomeGrafico);
            for(String puntoOra : grafico.getPunti()){
                if(!puntiVisitati.contains(puntoOra)){
                    dfs(puntoOra, null , grafico);
                }//end if - Controllo se il punto lo già visto
            }//end for - cicolo sui punti del Grafico
        }catch (ExeException e){
            throw e;
        }
        return returnPonti;
    }//calcola

    @Override
    public void help() {
        System.out.println("Ricerca di Ponti : ricerca del colemanteo tra punti che se tolto genera otteniamo un grafico in 2 blocchi");
    }//help

    public String printReturn(){
        if(returnPonti.isEmpty()){
            return "[ NO PONTO TROVATI ]";
        }else{
            StringBuilder stringReturn = new StringBuilder();
            int counter = 0;

            stringReturn.append("Ponti trovati ("+returnPonti.size()+") : \n");
            for (String ponte : returnPonti) {
                counter++;
                stringReturn.append(counter+") "+ returnPonti +"\n");
            }
            return stringReturn.toString();
        }
    }//printReturn

    public List<String> getReturnPonti(){return returnPonti;}

    /**********   PRIVATE      *******************/
    private void dfs(String u, String padre, VarGrafico grafico) throws ExeException {
        sps++;
        if(sps> SPS_MAX_LOOP){
            //Evito un cilco che risicha di essere infinito
            throw new ExeException(this, "dfs" , "System Protection : funzone ricorsiva troppe volte chiamta!");
        }

        puntiVisitati.add(u);
        tin.put(u, tempo);
        low.put(u, tempo);
        tempo++;

        for(String v : grafico.getGraficoDati().getOrDefault(u, new HashMap<>()).keySet()){
            if(v.equals(padre)){ continue; }// ignoriamo l’arco da cui siamo arrivati

            if(!puntiVisitati.contains(v)){
                dfs(v, u, grafico);
                // aggiornamento del valore low
                low.put(u, Math.min(low.get(u), low.get(v)));

                if(low.get(v) > tin.get(u)){
                    //E un ponte
                    returnPonti.add(u + " - " + v);
                }//end if - verifca se è un ponte
            }else {
                // arco di ritorno
                low.put(u, Math.min(low.get(u), tin.get(v))); //Estraggo il minimo tra i vaolro
            }//end if - punto Visitato
        }//end for - su tutti i punti del grafico

    }//dfs


}//GrafPonti
