/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Calcola il Centro dal Grafico
 */

package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.PKException;
import at.spengergasse.spoto.Terminale;

public class GrafCentro extends CMDBase {
    public GrafCentro(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {

    }//avvio

    public void calcola(String nomeGrafico) throws ExeException {
        if(!super.controllaPK()){ throw new PKException(this);}
    }//calcola

    @Override
    public void help() {
        System.out.println("Calcola il Centro da un Grafico");
    }//help
}//GrafCentro
