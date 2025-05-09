/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-09

    Aggiunta Manuale di un Grafico
*/
package at.spengergasse.spoto.CMD.Grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Terminale;

public class GrafAddManual extends CMDBase {

    //Variabili di Instanza
    VarGrafico nuovoGrafico;

    public GrafAddManual(Terminale terminal) {
        super(terminal);
    }


    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        //Variabili
        String nomeGarfico = super.inputString("Nome del garfico");
        Integer numeroPunti = super.inputInteger("Numero dei punti");

        nuovoGrafico = new VarGrafico(nomeGarfico);

        for(int i = 1; i <= numeroPunti; i++){
            String puntoNome = super.inputString("Inserire il nome del Punto ["+i+" di "+numeroPunti+"] ");
            Integer numeroColegamenti = super.inputInteger("Inserire il numero de colegamenti per il punto "+puntoNome);
            try {
                nuovoGrafico.addPunto(puntoNome);
                for(int j = 1; j <= numeroColegamenti; j++){
                    String colegantoPK = super.inputString("Inserire il nome dei Punti collegati ["+j+" di "+numeroColegamenti+"] con il Punto "+puntoNome);
                    nuovoGrafico.addColegamento(puntoNome, colegantoPK);
                }//for colegamenti

                //Funzone per la verifica del grafico
            }catch(ExeException e ){
                System.out.println(e);
                return;
            }
        }//For Punti

        try{
            nuovoGrafico.controllaGrafico();
        }catch(ExeException e ){
            System.out.println(e);
            return;
        }

        //Tutto a Posto salvo il Grafico
        terminal.addMappaGrafico(nomeGarfico , nuovoGrafico.clone());
        System.out.println("Grafico creato com sucesso ("+nomeGarfico+")");
    }//avvio

    @Override
    public void help() {
        System.out.println("Aggiungi manualmente un Grafico");
    }//help
}//GrafAddManual
