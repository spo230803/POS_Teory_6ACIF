/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Attiva o Disattiva la modalità DEBUG
 */
package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Main;
import at.spengergasse.spoto.Terminale;

public class Debug extends CMDBase {
    public Debug(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(Main.isDebug){
            Main.isDebug = false;
            System.out.println("Debug DISATTIVATO");
        }else {
            Main.isDebug = true;
            System.out.println("Debug ATTIVATO");
        }
    }

    @Override
    public void help() {
        System.out.println("Attiva o Disattiva la modalita Debug, ora il debug è "+ (Main.isDebug ? "ATTIVATO" : "DISATTIVATO"));
    }
}//Debug
