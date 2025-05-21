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
        MatAdd matriceCarica = new MatAdd(terminal);
        MatCreaGrafico matriceCreaGrafico = new MatCreaGrafico(terminal);
        GrafCaclolaDistanza graficoDistanza = new GrafCaclolaDistanza(terminal);
        GrafEscentricita grafEscentricita = new GrafEscentricita(terminal);
        GrafDiametro grafDiametro = new GrafDiametro(terminal);
        GrafCentro grafCentro = new GrafCentro(terminal);
        GraRaggio graRaggio = new GraRaggio(terminal);

        //Carico File
        passo++;
        System.out.println(passo+") Caricare il file Adjazenzmatrix che continee Adjazenzmatrix ");
        nomeMatrice = super.inputString("Nome matrice: ");
        String fileCSV = super.inputString("Percorso file: ");

        try {
            matriceCarica.caricaFile(fileCSV , nomeMatrice );
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }


        //Conve la matrice in Grafico
        passo++;
        System.out.println(passo+") Conversione della matrice in Grafico");
        try{
            nomeGrafico = matriceCreaGrafico.carica(nomeMatrice);
        }catch (ExeException e) {
            System.out.println(e);
            return;
        }


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




    }//avvio

    //TODO: da finire
    @Override
    public void help() {
        System.out.println("Esecuzione del Progetto in autmatico senza chiamare manuale le varei Funzioni");
    }//help
}//run
