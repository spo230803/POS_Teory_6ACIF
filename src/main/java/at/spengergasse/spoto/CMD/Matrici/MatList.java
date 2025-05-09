/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Lista di tutte le Matrici salvate
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.ArrayList;
import java.util.Collections;

public class MatList extends CMDBase {
    public MatList(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}
        ArrayList<String> listaChiave = new ArrayList<>(terminal.getMappaMatrici().keySet());
        Collections.sort(listaChiave);

        System.out.println("------------");
        if(listaChiave.size() == 0){
            System.out.println("Nessuna matrice trovata");
        }else {
            System.out.println("Sono state trovate " + listaChiave.size() + " matrici");
            System.out.println();
            for (String chiave : listaChiave) {
                System.out.println(chiave);
            }
        }
        System.out.println("------------");
    }//avvio

    @Override
    public void help() {
        System.out.println("Elenco di tutte le matrici disponibili");
    }
}//Lista
