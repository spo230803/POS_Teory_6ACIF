/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-09

    Aggiunta Manuale di un Grafico
*/
package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Terminale;

import java.util.HashMap;
import java.util.Map;

public class GrafAddManual extends CMDBase {

    //Variabili di Instanza
    VarGrafico nuovoGrafico;
    boolean graficoConPesi;

    public GrafAddManual(Terminale terminal) {
        super(terminal);
    }


    @Override
    public void avvio() {
        if (!super.controllaPK()) {
            return;
        }

        //Variabili
        String nomeGarfico = super.inputString("Nome del garfico");
        Integer numeroPunti = super.inputInteger("Numero dei punti");
        graficoConPesi = (super.inputInteger("Il grafico ha un peso? 0 = No | <> 0 Si") == 0 ? false : true);

        if(graficoConPesi){
            System.out.println("Graficio impostato CON Pesi");
            nuovoGrafico = new VarGrafico(nomeGarfico, true);
        }else {
            System.out.println("Graficio impostato SENZA Peso");
            nuovoGrafico = new VarGrafico(nomeGarfico, false);
        }

        try{
            for (int i = 1; i <= numeroPunti; i++) {
                String puntoNome = super.inputString("Inserire il nome del Punto [" + i + " di " + numeroPunti + "] ");
                Integer numeroColegamenti = super.inputInteger("Inserire il numero de colegamenti per il punto " + puntoNome);

                nuovoGrafico.addPunto(puntoNome);
                Map<String, Integer> colegamenti = new HashMap<String, Integer>();

                for (int j = 1; j <= numeroColegamenti; j++) {
                    String puntoCollegato = super.inputString("Inserire il Nome del  punto collegato [" + j + " di " + numeroColegamenti + "] per il punto  "+ puntoNome);
                    Integer peso;
                    if (graficoConPesi) {
                        peso = super.inputInteger("Inserire il peso del colegamento del punto " + puntoCollegato);
                    } else {
                        peso = 1;
                    }

                    colegamenti.put(puntoCollegato, peso);

                }//For Colegamenti
                nuovoGrafico.addColegamento(puntoNome , colegamenti);
            }//For Punti
        }catch(ExeException e ){
            System.out.println(e);
            return;
        }

        try{
            nuovoGrafico.controllaGrafico();
        }catch(ExeException e ){
            System.out.println(e);
            return;
        }

        //Tutto a Posto salvo il Grafico
        terminal.addPoolGrafico(nomeGarfico , nuovoGrafico.clone());
        System.out.println("Grafico creato com sucesso ("+nomeGarfico+")");
    }//avvio

    @Override
    public void help() {
        System.out.println("Aggiungi manualmente un Grafico");
    }//help
}//GrafAddManual
