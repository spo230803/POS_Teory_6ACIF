/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Variabile contente i Punti con relativi Valori
 */

package at.spengergasse.spoto.Libreria;


import at.spengergasse.spoto.Terminale;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class VarPunti extends VarBase{

    //Variabili in Instanza
    private Terminale terminal;
    private VarGrafico grafioOrigne;
    private Map<String, Integer> puntiDati = new HashMap<String, Integer>();
    private String notePunit;

    //Costruttori
    public VarPunti(Terminale terminal) {
        setTerminal(terminal);
    }
    public VarPunti(Terminale terminal , VarGrafico grafioOrigne) {
        setTerminal(terminal);
        setGrafioOrigne(grafioOrigne);
    }
    public VarPunti(Terminale terminal, VarGrafico grafioOrigne, String notePunti) {
        setTerminal(terminal);
        setGrafioOrigne(grafioOrigne);
        setNotePunit(notePunti);
    }
    public VarPunti(Terminale terminal, String notePunti) {
        setTerminal(terminal);
        setNotePunit(notePunti);
    }

    public void addValore(String puntoID , int val){
        puntiDati.put(puntoID, val);
    }//addValore

    @Override
    public String toString(){
        if(puntiDati == null){
            return "Nessun risultato da visualizare";
        }else {
            StringBuilder strReturn= new StringBuilder();
            strReturn.append("------------------------\n");
            strReturn.append("Punti dal Grfico: "+grafioOrigne.getNomeGrafico()+"\n");
            strReturn.append("Punti con il Valore: "+ notePunit +"\n");
            strReturn.append("{\n");
            for(String puntoID : puntiDati.keySet()){
                strReturn.append("\t"+puntoID+": "+ (puntiDati.get(puntoID) == Integer.MAX_VALUE ? "inf" : puntiDati.get(puntoID)) +"\n");
            }
            strReturn.append("}\n");
            return strReturn.toString();
        }
    }//toString

    @Override
    public VarPunti clone() {
        try {
            return (VarPunti) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai accadere
        }
    }
}//VarPunti
