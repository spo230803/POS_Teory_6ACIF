/*
    Autore  : SPOTO Giorgio Matteo
    Classe  : 6A CIF
    Ver     : 1.1.0
    del     : 2025-06-07

    Classe per la riceca dellle Articolazioni
*/


package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import java.util.*;
import static at.spengergasse.spoto.Main.SPS_MAX_LOOP;

public class GrafAricolazioni extends CMDBase {

    //Variabili di Istanza
    private Set<String> returnAricolazioni = new HashSet<>();
    private Set<String> puntoVisitato = new HashSet<>();
    private int sps = 0;

    private Map<String, Integer> disc = new HashMap<>();
    private Map<String, Integer> low = new HashMap<>();
    private Map<String, String> parent = new HashMap<>();
    VarGrafico grafico;
    private int tempo = 0;

    public GrafAricolazioni(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeGrafico = super.inputString("Nome Grafico");
        try {
            calcola(nomeGrafico);
            System.out.println(this.printReturn());
        }catch (ExeException e){
            System.out.println(e);
        }
    }//avvio

    public Set<String> calcola(String nomeGrafico) throws ExeException{
        //Verifico i Parametri
        try{
            super.controllaEsisteGrafico(nomeGrafico);
            grafico = terminal.getPoolGrafico().get(nomeGrafico);
        } catch (ExeException e) { throw e;}


        //Avvio la ricerca delle Articolazoini
        try {
            for (String punto : grafico.getPunti()) {
                if (!puntoVisitato.contains(punto)) {
                    dfs(punto, null);
                }//end if - controlo punto già visto
            }//end for - cicolo sui punit
        }catch (ExeException e){
            throw e;
        }
        return returnAricolazioni;
    }//calcola

    @Override
    public void help() {
        System.out.println("Cerca nel grafico le Aricolazioni (Punti che cancellati dividono il graifico in 2)");
    }//help

    public Set<String> getReturnAricolazioni(){
        return returnAricolazioni;
    }

    public String printReturn(){
        if(returnAricolazioni.isEmpty()){
            return  "[ NO DATI ]";
        }else {
            int count = 0;
            StringBuilder retrunString = new StringBuilder();
            retrunString.append("Articolazioni trovati ("+returnAricolazioni.size()+") :");

            for(String punto : returnAricolazioni){
                count++;
                retrunString.append(count+") "+punto+"\n");
            }
            return retrunString.toString();
        }
    }//printReturn

    //------------------   PRIVATE MEHTODE -------------
    private void dfs(String u, String padre) throws ExeException {
        sps++;
        if(sps > SPS_MAX_LOOP){
            //Evito un cilco che risicha di essere infinito
            throw new ExeException(this, "dfs" , "System Protection : funzone ricorsiva troppe volte chiamta!");
        }

        puntoVisitato.add(u);
        disc.put(u, tempo);
        low.put(u, tempo);
        tempo++;
        int figli = 0;

        for (String v : grafico.getGraficoDati().get(u).keySet()) {
            if (!puntoVisitato.contains(v)) {
                parent.put(v, u);
                figli++;
                dfs(v, u);

                // aggiorna low di u
                low.put(u, Math.min(low.get(u), low.get(v)));

                // caso 1: u è radice e ha almeno due figli
                if (padre == null && figli > 1) {
                    returnAricolazioni.add(u);
                }

                // caso 2: u non è radice e low[v] >= disc[u]
                if (padre != null && low.get(v) >= disc.get(u)) {
                    returnAricolazioni.add(u);
                }
            } else if (!v.equals(padre)) {
                // back edge
                low.put(u, Math.min(low.get(u), disc.get(v)));
            }
        }
    }//dfs

}//GarfAricolazioni
