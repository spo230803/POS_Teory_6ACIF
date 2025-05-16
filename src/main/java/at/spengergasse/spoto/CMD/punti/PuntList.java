/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Lista di tutte le variabili Punti in Memoria
 */

package at.spengergasse.spoto.CMD.punti;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.Map;
import java.util.Set;

public class PuntList extends CMDBase {
    public PuntList(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        Map print = terminal.getPoolPunti();
        Set<String> listaPunti = print.keySet();
        System.out.println("----------------");
        for(String val : listaPunti) {
            System.out.println(val);
        }
        System.out.println("----------------");
    }//avvio

    @Override
    public void help() {
        System.out.println("Visualizza elenco di tutte le Variabili Punti in Memoria");
    }//help
}//PuntList
