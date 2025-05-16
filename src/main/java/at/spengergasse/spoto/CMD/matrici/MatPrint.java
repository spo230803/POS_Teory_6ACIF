/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Stampa a video una Matrice
 */


package at.spengergasse.spoto.CMD.matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

public class MatPrint extends CMDBase {
    public MatPrint(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        String nomeMatrice = super.inputString("Nome della Matrice da stampare");
        VarMatrice printMatrice ;

        try {
            printMatrice =  terminal.getPoolMatrici().get(nomeMatrice);
        }catch(Exception e){
            ExeException errore = new ExeException(this , "getMappaMatrici", e.getMessage());
            System.out.println(errore);
            return;
        }

        if(printMatrice == null){
            System.out.println("Matrice non trovato ("+nomeMatrice+")");
        }else {
            System.out.println(printMatrice);
        }
    }//avvio

    @Override
    public void help() {
        System.out.println("Funzione per la Stampa della matrice Selezionata");
    }//help
}//Print
