package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Terminale;

public class GrafPrint extends CMDBase {
    public GrafPrint(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nome = super.inputString("Nome del Grafico da stampare");
        VarGrafico printGrafico;
        try{
            printGrafico = terminal.getPoolGrafico().get(nome);
        }catch(Exception e){
            ExeException errore = new ExeException(this , "getPoolGrafico", e.getMessage());
            System.out.println(errore);
            return;
        }
        if(printGrafico == null){
            System.out.println("Grafico non trovato : "+nome);
        }else {
            System.out.println(printGrafico);
        }
    }//avvio

    @Override
    public void help() {
        System.out.println("Stama il garfico in base al nome");
    }
}//GrafPrint
