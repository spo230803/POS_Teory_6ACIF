/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Da grafico a Matrice con i relativi Pesi
 */
package at.spengergasse.spoto.CMD.Grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Terminale;

public class GraCaclolaDistanza extends CMDBase {

    public GraCaclolaDistanza(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeGraficoToMatrice = super.inputString("Nome grafico per il calcolo delle Distanze");
        try {
            terminal.getMappaGrafico().get(nomeGraficoToMatrice);
        } catch (Exception e) {
            ExeException errore = new ExeException(this , "ricerca Grafico","Errore nel caricamento grafico ("+nomeGraficoToMatrice+") : "+ e.getMessage() );
            System.out.println(errore);
            return;
        }


    }//avvio

    @Override
    public void help() {
        System.out.println("Calcolo delle Distanze da un grafico - Restituisce una Matrice");
    }//help
}//GraCreateMatrix