package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.CMD.grafico.*;
import at.spengergasse.spoto.CMD.matrici.MatAdd;
import at.spengergasse.spoto.CMD.matrici.MatCreaGrafico;
import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;

public class Run extends CMDBase {

    //variabli Istanza
    private String nomeMatrice;
    private String nomeGrafico;

    public Run(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("Load....");
        //Variabili in Istanza
        int passo = 0;
        int diametro = 0;

        //Valri di Retunr
        String returnMatriceRadio;
        String returnMariceEscentricita;
        String returnMatriceDistanza;
        String returnPuntiCentro;
        int retrunDametro;
        int returnRadio;


        //Oggeti contenenti CMD
        MatAdd matriceCarica                    = new MatAdd(terminal);
        MatCreaGrafico matriceCreaGrafico       = new MatCreaGrafico(terminal);
        GrafCaclolaDistanza graficoDistanza     = new GrafCaclolaDistanza(terminal);
        GrafEscentricita grafEscentricita       = new GrafEscentricita(terminal);
        GrafDiametro grafDiametro               = new GrafDiametro(terminal);
        GrafCentro grafCentro                   = new GrafCentro(terminal);
        GrafRaggio graRaggio                    = new GrafRaggio(terminal);
        GrafDFS grafDFS                         = new GrafDFS(terminal);
        GrafAricolazioni grafAricolazioni       = new GrafAricolazioni(terminal);
        GrafPonti grafPonti                     = new GrafPonti(terminal);



        System.out.println();
        System.out.println("*********************************************");
        System.out.println("**********     AVVIO PROGRAMMA      *********");
        System.out.println("*********************************************");
        System.out.println();

        //Carico File
        passo++;
        System.out.println(passo+") Caricare il file Adjazenzmatrix che continee Adjazenzmatrix ");
        nomeMatrice = super.inputString("Nome matrice");
        String fileCSV = super.inputString("Percorso file");

        try {
            matriceCarica.caricaFile(fileCSV , nomeMatrice );
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("===========");


        //Conve la matrice in Grafico
        passo++;
        System.out.println(passo+") Conversione della matrice in Grafico");
        try{
            nomeGrafico = matriceCreaGrafico.carica(nomeMatrice);
        }catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Conversione avventuta con Successo");
        System.out.println("===========");


        //Calolo distanza
        passo++;
        System.out.println(passo+") Calcolo distanza");
        try{
            returnMatriceDistanza =  graficoDistanza.calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println(terminal.getPoolMatrici().get(returnMatriceDistanza));
        System.out.println("===========");


        //Exzentrizitaten
        passo++;
        System.out.println(passo+") Calcolo Exzentrizitaten");
        try{
            returnMariceEscentricita = grafEscentricita.calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println(terminal.getPoolPunti().get(returnMariceEscentricita));
        System.out.println("===========");

        //Radio
        passo++;
        System.out.println(passo+") Calcolo Radio");
        try{
            returnRadio = graRaggio.calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Radio :" + returnRadio);
        System.out.println("===========");

        //Diametro
        passo++;
        System.out.println(passo+") Calcolo Diametro");
        try {
            retrunDametro = grafDiametro.calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("Diamtro : "+retrunDametro);
        System.out.println("===========");


        //Centro
        passo++;
        System.out.println(passo+") Calcolo Centro");
        try {
            returnPuntiCentro = grafCentro.calcola(nomeGrafico);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println(terminal.getPoolPunti().get(returnPuntiCentro));
        System.out.println("===========");


        //Cacolo DFS
        passo++;
        System.out.println(passo+") Esecuzione del DFS");
        try{
            grafDFS.calcola(nomeGrafico);
            //Stampa a video il Risutato
            System.out.println("Nodi Trovati in DFS : "+grafDFS.getReturnComponenti());
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("===========");


        //Ricerca Articolazioni
        passo++;
        System.out.println(passo+") Ricerca Aricolazioni");
        try{
            grafAricolazioni.calcola(nomeGrafico);
            System.out.println("Articolazioni trovate : " + grafAricolazioni.getReturnAricolazioni());
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("===========");

        //Ricerca dei Ponti
        passo++;
        System.out.println(passo+") Ricerca Ponti");
        try {
            grafPonti.calcola(nomeGrafico);
            System.out.println(grafPonti.printReturn());
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }
        System.out.println("===========");

        System.out.println();
        System.out.println("*********************************************");
        System.out.println("**********     FINE PROGRAMMA      **********");
        System.out.println("*********************************************");
        System.out.println();

    }//avvio


    @Override
    public void help() {
        System.out.println("Esecuzione del Progetto in autmatico senza chiamare manuale le varei Funzioni");
    }//help
}//run
