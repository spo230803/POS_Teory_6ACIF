package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class Storia extends CMDBase {
    public Storia(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        int size = terminal.getCronologiaComandi().size();
        System.out.println("Comandi fin ora ustai : " + size );
        int start = Math.max(0, size - 5);
        for (int i = size - 1; i >= start; i--) {
            System.out.println("> "+terminal.getCronologiaComandi().get(i));
        }
        System.out.println("--------------");
    }//avvio

    @Override
    public void help() {
        System.out.println("Visualizia gli ultimi 5 comandi imessi");
    }//help
}//Storia
