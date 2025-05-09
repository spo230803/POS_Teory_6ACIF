/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Classe per la memorizazione del Grafico
 */


package at.spengergasse.spoto.Libreria;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class VarGrafico implements Cloneable{

    private String nomeGrafico;
    private Map<String , ArrayList<String>> graficoDati = new HashMap<>(); //Key = Lista Punti ; Array = Lista Colegamenti


    public VarGrafico(String nomeGrafico) {
        setNomeGrafico(nomeGrafico);
    }//Contruttor

    public void addPunto(String puntoID) throws ExeException {
        if(puntoID == null) { throw new ExeException(this,"addPunto" , "puntoID non può essere Vuoto" ); }

        try {
            if (esisteChiave(puntoID)) {
                throw new ExeException(this,"addPunto" , "puntoID già Presente" );
            }

            //il putno non esiste lo creo
            graficoDati.put(puntoID, new ArrayList<String>());
        }catch (Exception e){
            throw new ExeException(this,"addPunto - put" , e.getMessage() );
        }
    }//addPunto

    public void addColegamento(String puntoID , String colegamentoFK) throws ExeException {

        //Controllo Input
        if(colegamentoFK == null) { throw new ExeException(this, "addColegamento - Input" , "colegamentoFK non puo essere Vuoto");}
        try{
            if(!esisteChiave(puntoID)) { throw new ExeException(this,"addColegamento - Input" , "puntoID non Trovato ( "+puntoID+" )"); }
            if(!graficoDati.get(puntoID).add(colegamentoFK)){throw new ExeException(this , "addColegamento - add" , "Errore in inserimento");}
        }catch (Exception e){ throw new ExeException(this,"addColegamento - add" , e.getMessage() ); }

    }//addColegamento



    public boolean esisteChiave(String puntoID) throws ExeException {
        if(puntoID == null) { throw new ExeException(this,"esisteChiave" , "puntoID non può essere Vuoto" ); }
        if(graficoDati.containsKey(puntoID)) {return true;}
        else {return false;}
    }//esisteChiave


    public void controllaGrafico() throws ExeException {
        //Contorlla che i dati inseriti siano validi

        // Controlla che i dati inseriti siano validi
        for (Map.Entry<String, ArrayList<String>> varAttuale : graficoDati.entrySet()) {
            String puntoA = varAttuale.getKey();
            ArrayList<String> collegamentiA = varAttuale.getValue();

            for (String puntoB : collegamentiA) {
                // Controlla che il puntoB esista
                if (!this.esisteChiave(puntoB)) {
                    throw new ExeException(this, "controllaGrafico",
                            "Il Punto [" + puntoA + "] ha un collegamento con punto non valido: " + puntoB);
                }

                // Controlla che la connessione sia reciproca (simmetrica)
                ArrayList<String> collegamentiB = graficoDati.get(puntoB);
                if (collegamentiB == null || !collegamentiB.contains(puntoA)) {
                    throw new ExeException(this, "controllaGrafico",
                            "Connessione non simmetrica: [" + puntoA + "] è collegato a [" + puntoB +
                                    "], ma [" + puntoB + "] non è collegato a [" + puntoA + "]");
                }
            }
        }

    }//controllaGrafico

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("--------------------");
        str.append("\n");

        str.append("Nome Grafico: " + getNomeGrafico() + "\n");
        str.append("\n");
        for (Map.Entry<String, ArrayList<String>> varAttuale : graficoDati.entrySet()) {
            str.append("Punto -" + varAttuale.getKey() + "- Collegato con : \t");
            for(int i = 0 ; i < varAttuale.getValue().size() ; i++) {
                str.append(varAttuale.getValue().get(i) + " ; " );
            }//for Valore
            str.append("\n");
        }//for Chiave

        str.append("\n");
        str.append("--------------------");
        return str.toString();
    }//toString

    @Override
    public VarGrafico clone() {
        try {
            return (VarGrafico) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai accadere
        }
    }
}//VarGrafico
