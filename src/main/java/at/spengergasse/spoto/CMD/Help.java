/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Stampa a video tutti i comandi disponibili
 */

package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class Help extends CMDBase {

    public Help(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("------------");
        System.out.println("Elenco di tutti i Comandi disponibili:");
        System.out.println();
        for (String chiave : terminal.getMappaComandi().keySet()) {
            System.out.println(chiave);
        }
        System.out.println("------------");
    }

    @Override
    public void help() {
        System.out.println("Elenco dei Comandi disponibili. Usere il -comando ?- per maggiorni infomrazioni sul comando");
    }
}//Help
