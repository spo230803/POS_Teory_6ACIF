/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Cancella Matrice dalla Memoria
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class MatDelete extends CMDBase {

    public MatDelete(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        String nomeMatrice = super.inputString("Nome della Matrice da cancellare");

        Map matrici = terminal.getMappaMatrici();
        matrici.remove(nomeMatrice);
        System.out.println("Matrice cancelalta : "+nomeMatrice);
    }//avvio

    @Override
    public void help() {
        System.out.println("Cancella una Matrice dalla memoria");
    }
}//Delte
