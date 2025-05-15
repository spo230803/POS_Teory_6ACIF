/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-11

    Aggiunta di una matrice da un Graficoo
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Terminale;

public class MatAddDaGrafico extends CMDBase {
    public MatAddDaGrafico(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {

        //Variabili Locali
        String nomeGrafico;
        VarGrafico grafitoEntrata;

        if(!super.controllaPK()){return;}

        if(terminal.getMappaGrafico().size() == 0 ){
            System.out.println("ERROR : Nessun grafico caricato. Per caricare un grafico usare il comando  GRA_ADD oppure GRA_ADD_MANUAL");
            return;
        }

        //Seleziono il Grafico
        nomeGrafico = super.inputString("Nome del grafico");
        if(terminal.getMappaGrafico().get(nomeGrafico) == null){
            System.out.println("Graficon non trovato ("+nomeGrafico+")");
            return;
        }//end Esiste Grafico

        grafitoEntrata = terminal.getMappaGrafico().get(nomeGrafico).clone();



    }//avvio

    @Override
    public void help() {
        System.out.println("Crea una matricia da un grafico in meoria");
    }//help
}//MatAddDaGrafico
