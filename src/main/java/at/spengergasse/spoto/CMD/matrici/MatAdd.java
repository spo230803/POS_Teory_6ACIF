/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    aggiungi da file una Matrice
 */

package at.spengergasse.spoto.CMD.matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

public class MatAdd extends CMDBase {

    //Variabili di Istanza
    private VarMatrice matrice;


    public MatAdd(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("-----------");

        if(!super.controllaPK()){return;}


        String nomeMatrice = super.inputString("Nome della Matrice");
        String fileMatrice = super.inputString("Inserire file della Matrice");

        //Creo la Matirce
        matrice = new VarMatrice(terminal , nomeMatrice);

       if (caricaFile(fileMatrice , nomeMatrice)) {
           System.out.println("Matrice caricata correttamente : "+nomeMatrice);
       }else {
           System.out.println("Errore durante il caricamento della Matrice");
       }

    }//avvio

    private boolean caricaFile(String fileMatrice , String nomeMatrice) {
        try{
            matrice.caricaMatriceDaFile(fileMatrice , nomeMatrice);
        }catch (ExeException e) {
            System.out.println(e.getMessage());
            return false;
        }

       terminal.addPoolMatrici(nomeMatrice , matrice.clone());

        return true;
    }//caricaFile

    @Override
    public void help() {
        System.out.println("Aggiungi una nuova Matrice da File");
        System.out.println("Verra richiesto un nome Matrice e il percorso del file (es. C:\\User Matrice.txt)");
        System.out.println("Una riga per ogni riga di Marice e Valori seprarti con " + terminal.getSeparaValoreMarice());
    }
}//add
