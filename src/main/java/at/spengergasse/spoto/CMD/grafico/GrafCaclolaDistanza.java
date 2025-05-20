/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Da grafico a Matrice caclcolando la distanza
 */
package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.exception.NoDataException;
import at.spengergasse.spoto.Libreria.exception.PKException;
import at.spengergasse.spoto.Terminale;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GrafCaclolaDistanza extends CMDBase {

    //Variabili Istanza
    VarGrafico grafico;
    String nomeMatriceReturn = terminal.getNomeRisultato();
    VarMatrice matriceDistanza = new VarMatrice(nomeMatriceReturn);
    Map<String, Map<String, Integer>>  graficoDati;


    public GrafCaclolaDistanza(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {

        //Variabili Locali
        String nomeGraficoToMatrice = super.inputString("Nome grafico per il calcolo delle Distanze");

        try{
            calcola(nomeGraficoToMatrice);
        }catch (ExeException e) {
            System.out.println(e);
            return;
        }

        System.out.println("Matrice distanza createa con successo ("+nomeMatriceReturn+")");
        System.out.println(matriceDistanza);
    }//avvio

    public String calcola(String parNomeGrafico) throws ExeException {
        //Funzone per il popolamento della Matrice
        //      PARAMETRI
        // parNomeGrafico   Nome del grafico da calcolare la Matrice
        //
        //      RETRUN
        // nomeMatriceReturn = Nome della matrice Creata che è stata salvata nel Pool nel Terminale (Oggetto)

        if(!super.controllaPK()){
            throw new PKException(this);
        }
        final int maxInt = Integer.MAX_VALUE;

        //Verifico che esiste il Grafico
        try {
            super.controllaEsisteGrafico(parNomeGrafico);
        }catch (ExeException e) {
            throw e;
        }

        try {
            grafico =  terminal.getPoolGrafico().get(parNomeGrafico).clone();
            Libreria.debug(grafico);
            matriceDistanza.setGraficDati(grafico);
        } catch (Exception e) {
            ExeException errore = new ExeException(this , "ricerca Grafico","Errore nel caricamento grafico ("+parNomeGrafico+") : "+ e.getMessage() );
            throw errore;
        }

        graficoDati = grafico.getGraficoDati();
        Set<String> elencoPunti = graficoDati.keySet(); // tutti i nodi
        Map<String, Integer> nomeToIndice = new HashMap<>();
        Map<Integer, String> indiceToNome = new HashMap<>();
        int index = 0;

        for (String nome : graficoDati.keySet()) {
            nomeToIndice.put(nome, index);
            indiceToNome.put(index, nome);
            index++;
        }
        int V = nomeToIndice.size();


        try{
            //Setto per prima cosa tutta la matrice a 0
            matriceDistanza.setMariceVuota(elencoPunti.size() , elencoPunti.size() , 0);
            for(int i = 0 ; i < elencoPunti.size() ; i++){
                for(int j = 0 ; j < elencoPunti.size() ; j++){
                    if(i != j) {matriceDistanza.setValore(i, j, maxInt);}
                }
            }

            //Aggiorno Matrice con Peso dal Graffico
            for (Map.Entry<String, Map<String, Integer>> entry : graficoDati.entrySet()) {
                String da = entry.getKey();
                int indiceDa = nomeToIndice.get(da);

                for (Map.Entry<String, Integer> collegamento : entry.getValue().entrySet()) {
                    String a = collegamento.getKey();
                    int indiceA = nomeToIndice.get(a);
                    int peso = collegamento.getValue();
                    Libreria.debug(da + " -> "+ a + " : " + peso );

                    matriceDistanza.setValore(indiceDa, indiceA, peso);
                }
            }


            // Algoritmo di Floyd-Warshall
            for (int k = 0; k < V; k++) {
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        int dik = matriceDistanza.getValore(i, k);
                        int dkj = matriceDistanza.getValore(k, j);
                        int dij = matriceDistanza.getValore(i, j);

                        if (dik != maxInt && dkj != maxInt && dij > dik + dkj) {
                            matriceDistanza.setValore(i, j, dik + dkj);
                        }
                    }//for J
                }//for I
            }//for K
        }catch (ExeException e){
            throw e;
        }

        //Salvataggo
        terminal.addPoolMatrici(nomeMatriceReturn , matriceDistanza.clone());
        return nomeMatriceReturn;
    }//calcoloDistanza

    @Override
    public void help() {
        System.out.println("Calcolo delle Distanze da un grafico - Salva il risutato in una Matrice");
        System.out.println("Utilizzo del Argoritmo : Floyd-Warshall");
    }//help
}//GraCreateMatrix