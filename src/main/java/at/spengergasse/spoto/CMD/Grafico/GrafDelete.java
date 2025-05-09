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
        String nome = terminal.terminaleGetInput("Nome del Grafico da cancellare");

        if(nome == null || nome.equals("")){
            avvio();
            return;
        }

        Map grafico = terminal.getMappaMatrici();
        grafico.remove(nome);
        System.out.println("Grafico cancellato : " + grafico);
    }//avvio

    @Override
    public void help() {
        System.out.println("Cancella un Grafico dalla memora in base la Nome");
    }//help
}//GrafDelete
