/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-09

    Lista di tutti i Grafici in Memoria
 */

package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import java.util.ArrayList;
import java.util.Collections;

public class GrafList extends CMDBase {
    public GrafList(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}
        ArrayList<String> listaNomeGraffico = new ArrayList<>(terminal.getPoolGrafico().keySet());
        Collections.sort(listaNomeGraffico);



        System.out.println("------------");
        if(listaNomeGraffico.size() == 0){
            System.out.println("Nessun grafico trovato");
        }else {
            System.out.println("Sono state trovate " + listaNomeGraffico.size() + " grafici");
            System.out.println();
            for (String chiave : listaNomeGraffico) {
                System.out.println(chiave);
            }
        }
        System.out.println("------------");

    }//avvio

    @Override
    public void help() {
        System.out.println("Lista dei Grafici Salvati");
    }//help
}//GrafList
