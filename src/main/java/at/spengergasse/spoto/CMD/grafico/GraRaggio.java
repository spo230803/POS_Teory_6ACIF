package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.ExeException;
import at.spengergasse.spoto.Libreria.VarPunti;
import at.spengergasse.spoto.Terminale;
import org.springframework.data.jpa.repository.query.JSqlParserUtils;

import java.util.HashMap;
import java.util.Map;

public class GraRaggio extends CMDBase {
    public GraRaggio(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {

        //Variabli Locali
        GraEscentricita escentricita = new GraEscentricita(terminal);
        String returnNomePunti;
        VarPunti puntiDati = new VarPunti(terminal , "Raggio");
        String nomeGrafico = super.inputString("Nome grafico: ");
        int minRadio = Integer.MAX_VALUE;

        //Estrago per prima cosa la escentricita
        try {
            returnNomePunti =  escentricita.calcola(nomeGrafico);
            puntiDati = terminal.getPoolPunti().get(returnNomePunti).clone();
        }catch (ExeException e ){
            System.out.println(e);
        }

        //Calcolo del Raggio (Valore Minio)
        for (Map.Entry<String, Integer> entry : puntiDati.getPuntiDati().entrySet()) {
            if(entry.getValue() < minRadio){
                minRadio = entry.getValue();
            }
        }
        System.out.println("Grafico ("+nomeGrafico+") Radius : "+minRadio);
    }//avvio

    @Override
    public void help() {
        System.out.println("Calcoal il Raggio da un Grafic");
    }//help
}//GraRaggio
