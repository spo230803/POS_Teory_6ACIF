/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06
 */
package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import static at.spengergasse.spoto.Main.PRODUT_KEY;

public class PK extends CMDBase {

    public PK(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String newPK = terminal.terminaleGetInput("Inserire il Produk key");
        if(newPK == null || newPK.equals("")) {
            avvio();
            return;
        }
        terminal.setProdutKeyUser(newPK);
        if(super.controllaPK()){
            System.out.println("Produk key e programma avvioato con successo :-)");
        }else {
            System.out.println("Produk key non valido! Programma attivo in modalità restrizione!");
        }
    }//avvio

    @Override
    public void help() {
        System.out.println("Imposta il Produk Key, per l'utizzo del programma");
    }//help
}//Pk
