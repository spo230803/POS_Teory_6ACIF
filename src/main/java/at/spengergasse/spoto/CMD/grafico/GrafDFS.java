package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import lombok.Getter;

import java.util.*;

import static at.spengergasse.spoto.Main.SPS_MAX_LOOP;
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
            grafico = terminal.getPoolGrafico().get(nomeGraficoInput).clone();

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
        if(sps > SPS_MAX_LOOP){
            //Evito un cilco che risicha di essere infinito
            throw new ExeException(this, "calcolaDFS" , "System Protection : funzone ricorsiva troppe volte chiamta!");
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

    //Calcoa_DFS Con pesi
    public void calcolaDFSConDistanza(String nodoOra, String nomeGrafico, Set<String> visitati, Map<String, Integer> distanze, int distanzaCorrente) throws ExeException {
        if (visitati.contains(nodoOra)) return;

        visitati.add(nodoOra);
        distanze.put(nodoOra, distanzaCorrente);

        VarGrafico grafico = terminal.getPoolGrafico().get(nomeGrafico);
        Map<String, Integer> vicini = grafico.getGraficoDati().getOrDefault(nodoOra, Collections.emptyMap());

        for (String vicino : vicini.keySet()) {
            if (!visitati.contains(vicino)) {
                calcolaDFSConDistanza(vicino, nomeGrafico, visitati, distanze, distanzaCorrente + 1);
            }
        }
    }
    //calcolaDFSConDistanza

    @Override
    public void help() {
        System.out.println("Comando per il Calcolo Depth-First Search da un Grafico");
    }//help
}//GrafDFS
