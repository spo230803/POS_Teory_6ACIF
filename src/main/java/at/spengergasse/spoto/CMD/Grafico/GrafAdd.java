/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Aggiungi da file un grafico

    Il file deve essere formanto con

    IDpunto, FKpuntoColegamento_1 ,FKpuntoColegamento_2...
 */

package at.spengergasse.spoto.CMD.Grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class GrafAdd extends CMDBase {
    public GrafAdd(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("VBGIO : Da implementare");
    }

    @Override
    public void help() {
        System.out.println("Aggiunta in memoria di un Grafico da File");
        System.out.println("Il file devve avere questo formato : Punto, coleamento,coleamento ... (un punto per riga)");
    }
}
