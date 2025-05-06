/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Impota il separatore dei valri delle Matrice
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class SetSeparaValore extends CMDBase {
    public SetSeparaValore(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("-------------------");
        String separaMatrice =  terminal.terminaleGetInput("Inserire il carattere di saperazione della Matrice");

        //Controlla Input
        if(separaMatrice.equals("") || separaMatrice==null ){
            avvio();
            return;
        }

        //Salvo impostazione
        terminal.setSeparaValoreMarice(separaMatrice);
        System.out.println("Nuovo separa marice: " + separaMatrice);
    }//avvio

    @Override
    public void help() {
        System.out.println("Imposta il carattere per separare valore nella riga Matrice");
        System.out.println("Valore attuale "+terminal.getSeparaValoreMarice());
    }//help
}//SetSeparaValore
