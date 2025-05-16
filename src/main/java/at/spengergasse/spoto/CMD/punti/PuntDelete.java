/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-09

    Cancella gruppo Piunti dalla Memoria
*/

package at.spengergasse.spoto.CMD.punti;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class PuntDelete extends CMDBase {
    public PuntDelete(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}
        String nome = terminal.terminaleGetInput("Nome dei Punti da cancellare");


        Map punti = terminal.getPoolPunti();
        punti.remove(nome);
        System.out.println("Punti cancellati : " + nome);
    }//avvio

    @Override
    public void help() {
        System.out.println("Elimina dalla memoria Gruppo Punti in base al nome");
    }//help
}//PuntDelete
