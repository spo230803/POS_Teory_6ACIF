/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Aggiungi da file un grafico

    Il file deve essere formanto con

    IDpunto, FKpuntoColegamento_1  , peso_1 ,FKpuntoColegamento_2, peso_1
 */

package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Terminale;

public class GrafAdd extends CMDBase {

    VarGrafico nuovoGrafico;
    boolean graficoConPesi;

    public GrafAdd(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        //inpiut
        if(!super.controllaPK()){return;}
        String nomeGrafico = super.inputString("Nome grafico");
        //graficoConPesi = (super.inputInteger("Il grafico ha un peso? 0 = No | <> 0 Si") == 0 ? false : true);
        graficoConPesi = super.inputBoolean("Il grafico ha un peso?");
        String nomeFile = super.inputString("Inserire il Perscorso del File del grafico");

        //Creo la Variabile con il Nuovo Grafico
        nuovoGrafico = new VarGrafico( this.terminal,nomeGrafico , graficoConPesi);

        try{
            nuovoGrafico.caricaGraficDaFile(nomeFile , nomeGrafico , graficoConPesi );
        } catch (ExeException e) {
            System.out.println(e.getMessage());
            return;
        }

        terminal.addPoolGrafico(nomeGrafico , nuovoGrafico.clone());
        System.out.println("Grfico caricato correttamente ("+nomeGrafico+")");
    }//avvio


    @Override
    public void help() {
        System.out.println("Aggiunta in memoria di un Grafico da File");
        System.out.println("CON PESI : Il File devev avere questo formato  : IDpunto ; FKpuntoColegamento_1 ; peso_1 ,FKpuntoColegamento_2 ; peso_1 ; ... ");
        System.out.println("SENZA PESI : Il File devev avere questo formato  : IDpunto ; FKpuntoColegamento_1 ; FKpuntoColegamento_2 ; ...  ");
    }//help
}//
