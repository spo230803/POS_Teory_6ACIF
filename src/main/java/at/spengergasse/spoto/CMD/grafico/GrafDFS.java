package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import lombok.Getter;

import java.util.*;

import static at.spengergasse.spoto.Main.SETTIING_MAX_MATRICE;
import static at.spengergasse.spoto.Main.isDebug;

@Getter
public class GrafDFS extends CMDBase {

    private int sps = 0;
    private List<Set<String>> returnComponenti = new ArrayList<>();

    public GrafDFS(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        //Variabli Locale
        String nomeGraficoInput = super.inputString("Nome Grafico per il calcolo DFS");
        Set<String> visitati = new HashSet<>();
        VarGrafico grafico;



        try {
            super.controllaEsisteGrafico(nomeGraficoInput);
            grafico = terminal.getPoolGrafico().get(nomeGraficoInput);

            super.debug("Stampa punti grafico" , grafico.getPunti().toString());

            for (String puntoOra : grafico.getPunti()) {
                if (!visitati.contains(puntoOra)) {
                    Set<String> componente = new HashSet<>();
                    calcolaDFS(puntoOra, nomeGraficoInput, visitati, componente);
                    returnComponenti.add(componente);
                }
            }
        } catch (ExeException e){
            System.out.println(e);
        }//end try

        //Preparo i dati per la Stampa
        int i = 1;
        for (Set<String> comp : returnComponenti) {
            System.out.println("Componente " + (i++) + ": " + comp);
        }

    }//Avvio

    public void calcola(String nomeGraficoInput) throws ExeException{
        //Stesso metdoo di "avvio" ma non stampa nulla
        // il ritorno si trova nella variabile di Instanza (returnComponeti)

        Set<String> visitati = new HashSet<>();
        VarGrafico grafico;
        try {
            super.controllaEsisteGrafico(nomeGraficoInput);
            grafico = terminal.getPoolGrafico().get(nomeGraficoInput);

            super.debug("Stampa punti grafico" , grafico.getPunti().toString());

            for (String puntoOra : grafico.getPunti()) {
                if (!visitati.contains(puntoOra)) {
                    Set<String> componente = new HashSet<>();
                    calcolaDFS(puntoOra, nomeGraficoInput, visitati, componente);
                    returnComponenti.add(componente);
                }
            }
        } catch (ExeException e){
            System.out.println(e);
        }//end try
    }//calcola

    public void calcolaDFS(String nodoOra, String nomeGrafico , Set<String> visitati, Set<String> componente ) throws ExeException {

        //Variabilil Locali
        VarGrafico grafico;

        //Controllo i parametri prima di Iniziare
        if(!super.controllaPK()){ throw new ExeException(this, "calcolaDFS", "PK non Valido!");}
        sps++;
        if(sps > SETTIING_MAX_MATRICE){
            //Evito un cilco che risicha di essere infinito
            throw new ExeException(this, "calcola_DFS" , "System Protection : troppe volte è stata chamata la stessa funzione");
        }

        //Prendo il grafico dalla Memoria
        try{
            super.controllaEsisteGrafico(nomeGrafico);
            grafico = terminal.getPoolGrafico().get(nomeGrafico);
        } catch (ExeException e) {
            throw e;
        }

        //Verifico che il nodo non sia già stato Visistato
        if(visitati.contains(nodoOra)){ return;}

        if(isDebug){System.out.println("Nodo ora : "+ nodoOra);}
        //Non è stato ancora visitato lo aggiungo
        visitati.add(nodoOra);
        componente.add(nodoOra);
        //Cicolo per i nodi -> Dal grafico estraggo il NodoOra e se non c'è Estraggo un
        for(String nodoPuntuale : grafico.getGraficoDati().getOrDefault(nodoOra, Collections.emptyMap()).keySet() ){
            calcolaDFS(nodoPuntuale, nomeGrafico, visitati,  componente);
        }//end for


    }//calcola_DFS

    @Override
    public void help() {
        System.out.println("Comando per il Calcolo Depth-First Search da un Grafico");
    }//help
}//GrafDFS
