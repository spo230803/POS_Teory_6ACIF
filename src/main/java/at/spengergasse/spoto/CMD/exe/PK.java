/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Imposta il PK del programma
 */
package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class PK extends CMDBase {

    public PK(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String newPK = super.inputString("Inserire il Produk key");
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
