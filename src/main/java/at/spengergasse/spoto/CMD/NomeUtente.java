package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class NomeUtente extends CMDBase {
    public NomeUtente(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeUtente = this.terminal.terminaleGetInput("Inserire nome Utente");
        if(nomeUtente.equals("") || nomeUtente == null) {
            this.avvio();
        }
        this.terminal.setNomeUtente(nomeUtente);
        this.terminal.cancellaConsole();
        System.out.println("Benvenuto Utente : " + nomeUtente);
    }

    @Override
    public void help() {
        System.out.println("Imposta il nome Utente");
    }


}//NomeUtente
