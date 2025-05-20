package at.spengergasse.spoto.CMD.punti;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.VarPunti;
import at.spengergasse.spoto.Terminale;

public class PuntPrint extends CMDBase  {
    public PuntPrint(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}
        String nome = super.inputString("Nome deli Punti da stampare");
        VarPunti puntiDati;
        try{
            puntiDati = terminal.getPoolPunti().get(nome);
        }catch(Exception e){
            ExeException errore = new ExeException(this , "getPoolGrafico", e.getMessage());
            System.out.println(errore);
            return;
        }
        if(puntiDati == null){
            System.out.println("Punti non trovato : "+nome);
        }else {
            System.out.println(puntiDati);
        }
    }//Avvio

    @Override
    public void help() {
        System.out.println("Stampa i punti di un grafico in base al nome del Gruppo Punti");
    }//help
}//PuntPrint
