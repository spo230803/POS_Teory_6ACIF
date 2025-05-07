/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    aggiungi da file una Matrice
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

public class Add extends CMDBase {

    //Variabili di Istanza
    private VarMatrice matrice = new VarMatrice();


    public Add(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("-----------");

        if(!super.controllaPK()){return;}

        String nomeMatrice =  terminal.terminaleGetInput("Inserire nome Matrice");
        String fileMatrice =  terminal.terminaleGetInput("Inserire file della Matrice");
        if (nomeMatrice == null || nomeMatrice.isEmpty()) {
            System.out.println("Nome Matrice non valido");
            avvio();
            return;
        }
        if (fileMatrice == null || fileMatrice.isEmpty()) {
            System.out.println("File della Matrice non valido");
            avvio();
            return;
        }

       if (caricaFile(fileMatrice , nomeMatrice)) {
           System.out.println("Matrice caricata correttamente");
       }else {
           System.out.println("Errore durante il caricamento della Matrice");
       }

    }//avvio

    private boolean caricaFile(String fileMatrice , String nomeMatrice) {
        try{
            matrice.caricaMatriceDaFile(fileMatrice , nomeMatrice);
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

       terminal.setMappaMatrici(nomeMatrice , matrice.clone());

        return true;
    }//caricaFile

    @Override
    public void help() {
        System.out.println("Aggiungi una nuova Matrice da File");
        System.out.println("Verra richiesto un nome Matrice e il percorso del file (es. C:\\User Matrice.txt)");
        System.out.println("Una riga per ogni riga di Marice e Valori seprarti con " + terminal.getSeparaValoreMarice());
    }
}//add
