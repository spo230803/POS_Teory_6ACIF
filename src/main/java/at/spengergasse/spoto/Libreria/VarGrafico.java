/*
    Autore  : SPOTO Giorgio Matteo
    Classe  : 6A CIF
    Ver     : 1.1.0
    del     : 2025-05-15

    Classe per la memorizazione del Grafico
 */


package at.spengergasse.spoto.Libreria;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class VarGrafico implements Cloneable{

    private String nomeGrafico;
    private boolean graficContienePeso;
    private Map<String , Map<String , Integer>> graficoDati = new HashMap<>(); //Key = Lista Punti ; Array = Lista Colegamenti


    //Contruttor
    public VarGrafico(String nomeGrafico) {
        setNomeGrafico(nomeGrafico);
    }
    public VarGrafico(String nomeGrafico, boolean graficContienePeso) {
        setNomeGrafico(nomeGrafico);
        this.graficContienePeso = graficContienePeso;
    }

    public void addPunto(String puntoID) throws ExeException {
        if(puntoID == null) { throw new ExeException(this,"addPunto" , "puntoID non può essere Vuoto" ); }

        try {
            if (esisteChiave(puntoID)) {
                throw new ExeException(this,"addPunto" , "puntoID già Presente" );
            }
            //il putno non esiste lo Creo
            graficoDati.put(puntoID, new HashMap<String, Integer>());
        }catch (Exception e){
            throw new ExeException(this,"addPunto - put" , e.getMessage() );
        }
    }//addPunto

    public void addColegamento(String puntoID , Map<String , Integer> colegamentoPeso) throws ExeException {
        //Controllo Input
        if(colegamentoPeso == null) { throw new ExeException(this, "addColegamento - Input" , "colegamentoFK non puo essere Vuoto");}
        try{
            if(!esisteChiave(puntoID)) { throw new ExeException(this,"addColegamento - Input" , "puntoID non Trovato ( "+puntoID+" )"); }

            Map<String, Integer> collegamenti = graficoDati.get(puntoID);

            for (Map.Entry<String, Integer> entry : colegamentoPeso.entrySet()) {
                String destinazione = entry.getKey();
                Integer peso = (graficContienePeso ?  entry.getValue() : 1);
                collegamenti.put(destinazione, peso);
            }

        }catch (Exception e){ throw new ExeException(this,"addColegamento - add" , e.getMessage() ); }

    }//addColegamento



    public boolean esisteChiave(String puntoID) throws ExeException {
        if(puntoID == null) { throw new ExeException(this,"esisteChiave" , "puntoID non può essere Vuoto" ); }
        if(graficoDati.containsKey(puntoID)) {return true;}
        else {return false;}
    }//esisteChiave


    public void controllaGrafico() throws ExeException {
        // Controlla che tutti i punti abbiano collegamenti validi e simmetrici
        for (Map.Entry<String, Map<String, Integer>> varAttuale : graficoDati.entrySet()) {
            String puntoA = varAttuale.getKey();
            Map<String, Integer> collegamentiA = varAttuale.getValue();

            for (String puntoB : collegamentiA.keySet()) {
                // 1. Controlla che puntoB esista nel grafo
                if (!this.esisteChiave(puntoB)) {
                    throw new ExeException(this, "controllaGrafico",
                            "Il punto [" + puntoA + "] ha un collegamento con punto non valido: " + puntoB);
                }

                // 2. Controlla che la connessione sia simmetrica
                Map<String, Integer> collegamentiB = graficoDati.get(puntoB);
                if (collegamentiB == null || !collegamentiB.containsKey(puntoA)) {
                    throw new ExeException(this, "controllaGrafico",
                            "Connessione non simmetrica: [" + puntoA + "] è collegato a [" + puntoB +
                                    "], ma [" + puntoB + "] non è collegato a [" + puntoA + "]");
                }

                // 3. Controlla che il peso sia simmetrico
                Integer pesoAB = collegamentiA.get(puntoB);
                Integer pesoBA = collegamentiB.get(puntoA);
                if (!pesoAB.equals(pesoBA)) {
                    throw new ExeException(this, "controllaGrafico",
                            "Peso non simmetrico tra [" + puntoA + "] e [" + puntoB + "]: " +
                                    "peso A→B = " + pesoAB + ", peso B→A = " + pesoBA);
                }
            }//for controllo colegamenti
        }//For per punti
    }//controllaGrafico

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stato attuale del grafo:\n");

        for (Map.Entry<String, Map<String, Integer>> entry : graficoDati.entrySet()) {
            String punto = entry.getKey();
            Map<String, Integer> collegamenti = entry.getValue();

            sb.append(punto).append(" -> ");

            if (collegamenti.isEmpty()) {
                sb.append("{}");
            } else {
                sb.append("{");
                int count = 0;
                for (Map.Entry<String, Integer> collegamento : collegamenti.entrySet()) {
                    sb.append(collegamento.getKey()).append("=").append(collegamento.getValue());
                    if (++count < collegamenti.size()) {
                        sb.append(", ");
                    }
                }
                sb.append("}");
            }

            sb.append("\n");
        }

        return sb.toString();
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
