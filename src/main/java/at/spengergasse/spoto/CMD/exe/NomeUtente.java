/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Imposta il nome Utente
 */
package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class NomeUtente extends CMDBase {
    public NomeUtente(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeUtente = super.inputString("Inserire nome Utente");
        this.terminal.setNomeUtente(nomeUtente);
        this.terminal.cancellaConsole();
        System.out.println("Benvenuto Utente : " + nomeUtente);
    }

    @Override
    public void help() {
        System.out.println("Imposta il nome Utente");
    }


}//NomeUtente
