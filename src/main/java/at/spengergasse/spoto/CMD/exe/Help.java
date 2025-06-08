/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Stampa a video tutti i comandi disponibili
 */

package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static at.spengergasse.spoto.Main.isDebug;

public class Help extends CMDBase {

    public Help(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        int countComandi = 0;
        System.out.println("------------");
        System.out.println("Elenco di tutti i Comandi disponibili:");
        System.out.println();

        List<String> listaChiave = new ArrayList<>(terminal.getMappaComandi().keySet());
        Collections.sort(listaChiave);

        for (String chiave : listaChiave) {
            countComandi++;
            if(isDebug) {
                System.out.println(chiave + "\t\t >> \t\t" + terminal.getMappaComandi().get(chiave).getName());
            }else {
                System.out.println(chiave);
            }
        }
        System.out.println();
        System.out.println("Comandi Trovai : " +countComandi);
        System.out.println("Scriveere il comando seguito da ? per maggiorni informazioni sul qeul comando");
        System.out.println("Scrivere in Input 'stop' per l'arresto d'Emergenza del Programma!");
        System.out.println("------------");
    }

    @Override
    public void help() {
        System.out.println("Elenco dei Comandi disponibili. Usere il -comando ?- per maggiorni infomrazioni sul comando");
    }
}//Help
