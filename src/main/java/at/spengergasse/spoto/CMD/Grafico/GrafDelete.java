/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-09

    Cancella un Grafico dalla Memoria
*/
package at.spengergasse.spoto.CMD.Grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class GrafDelete extends CMDBase {

    public GrafDelete(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}
        String nomeGrafico = terminal.terminaleGetInput("Nome del Grafico da cancellare");


        Map grafico = terminal.getMappaMatrici();
        grafico.remove(nomeGrafico);
        System.out.println("Grafico cancellato : " + nomeGrafico);
    }//avvio

    @Override
    public void help() {
        System.out.println("Cancella un Grafico dalla memora in base la Nome");
    }//help
}//GrafDelete
