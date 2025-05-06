package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class Exit extends CMDBase {
    public Exit(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("Chiusura del programma in corso ...");
        System.exit(0);
    }

    @Override
    public void help() {
        System.out.println("Permette di uscire dal programma");
    }
}//Exit
