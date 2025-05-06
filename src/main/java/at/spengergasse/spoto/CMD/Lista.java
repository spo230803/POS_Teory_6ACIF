package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class Lista extends CMDBase {
    public Lista(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        int count = 0;

        System.out.println("------------");
        for (String chiave : terminal.getMappaMatrici().keySet()) {
            System.out.println(chiave);
            count++;
        }
        if(count == 0) {
            System.out.println("Nessuna matrice trovata");
        }
        System.out.println("------------");
    }

    @Override
    public void help() {
        System.out.println("Elenco di tutte le matrici disponibili");
    }
}//Lista
