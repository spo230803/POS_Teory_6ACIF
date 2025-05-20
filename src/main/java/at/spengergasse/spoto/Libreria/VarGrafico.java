/*
    Autore  : SPOTO Giorgio Matteo
    Classe  : 6A CIF
    Ver     : 1.1.1
    del     : 2025-05-15

    Classe per la memorizazione del Grafico
 */


package at.spengergasse.spoto.Libreria;

import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Getter
@Setter
public class VarGrafico extends VarBase{

    private Terminale terminale;
    private String nomeGrafico;
    private boolean isGraficContienePeso;
    private VarMatrice matriceOrigne;
    private Map<String , Map<String , Integer>> graficoDati = new HashMap<>(); //Key = Lista Punti ; Map<puntoID , Peso> = Lista Colegamenti


    //Contruttor
    public VarGrafico(Terminale terminale) {setTerminale(terminale);}
    public VarGrafico(String nomeGrafico) {
        setNomeGrafico(nomeGrafico);
    }
    public VarGrafico(String nomeGrafico, boolean graficContienePeso) {
        setNomeGrafico(nomeGrafico);
        this.isGraficContienePeso = graficContienePeso;
    }
    public VarGrafico(Terminale terminale, String nomeGrafico , boolean graficContienePeso) {
        setTerminale(terminale);
        setNomeGrafico(nomeGrafico);
        this.isGraficContienePeso = graficContienePeso;
    }
    public VarGrafico(Terminale terminale, String nomeGrafico) {
        setTerminale(terminale);
        setNomeGrafico(nomeGrafico);
    }

    public void addPunto(String puntoID) throws ExeException {
        addPunto(puntoID , false);
    }//addPunto

    public void addPunto(String puntoID ,boolean siamoInFile) throws ExeException {
        if(puntoID == null) { throw new ExeException(this,"addPunto" , "puntoID non può essere Vuoto" ); }

        try {
            if (esisteChiave(puntoID)) {
                if(siamoInFile){return;}
                else{
                    //Inserimento Manuale
                    throw new ExeException(this,"addPunto" , "puntoID già Presente ("+puntoID+")" );
                }
            }
            //il putno non esiste lo Creo
            graficoDati.put(puntoID, new HashMap<String, Integer>());
        }catch (Exception e){
            throw new ExeException(this,"addPunto - put" , e.getMessage() );
        }
    }

    public void addColegamento(String puntoID , Map<String , Integer> colegamentoPeso) throws ExeException {
        //Controllo Input
        if(colegamentoPeso == null) { throw new ExeException(this, "addColegamento - Input" , "colegamentoFK non puo essere Vuoto");}
        try{
            if(!esisteChiave(puntoID)) { throw new ExeException(this,"addColegamento - Input" , "puntoID non Trovato ( "+puntoID+" )"); }

            Map<String, Integer> collegamenti = graficoDati.get(puntoID);

            for (Map.Entry<String, Integer> entry : colegamentoPeso.entrySet()) {
                String destinazione = entry.getKey();
                Integer peso = (isGraficContienePeso ?  entry.getValue() : 1);
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


    public void caricaGraficDaFile( String fileGrafico , String nomeGrafico , boolean graficContienePeso) throws ExeException {
        //Funzione per caricare un grafico da File

        //Controllo Parametri
        if(nomeGrafico == null || nomeGrafico.equals("")){
            throw new ExeException(this, "caricaGraficDaFile - par nomeGrafico" , "nomeGrafico non puo essere Vuoto");
        }
        if(fileGrafico == null || fileGrafico.equals("")){
            throw new ExeException(this, "caricaGraficDaFile - par fileGrafico" , "fileGrafico non puo essere Vuoto");
        }
        File graficoFile = new File(fileGrafico);
        if(!graficoFile.exists()){
            throw new ExeException(this,"caricaGraficDaFile - par fileGrafico" , "File non Trovato ("+fileGrafico+") ");
        }
        if(!graficoFile.isFile()){
            throw new ExeException(this,"caricaGraficDaFile - par fileGrafico" , "Non è un file! ("+fileGrafico+") ");
        }

        //Leggo il File
        try{
            FileReader graficoFileReadr = new FileReader(graficoFile);
            BufferedReader graficoBufferReadr = new BufferedReader(graficoFileReadr);
            String linea;

            //Lettura del File per Riga
            while ((linea = graficoBufferReadr.readLine()) != null) {
                linea = linea.trim();
                if(linea.equals("")){continue;} //Salto le linee vuote

                String[] valore = linea.split(terminale.getSeparaValoreMarice());
                if (valore.length < 2) {
                    throw new ExeException(this, "caricaGraficDaFile - formato riga", "Riga non valida: " + Arrays.toString(valore));
                }
                String puntoID = valore[0].trim();
                this.addPunto(puntoID , true);

                if (graficContienePeso) {
                    if ((valore.length - 1) % 2 != 0) {
                        throw new ExeException(this, "caricaGraficDaFile - parsing pesi", "Formato errato (manca un peso?): " + linea);
                    }

                    for (int i = 1; i < valore.length; i += 2) {
                        String destinazione = valore[i].trim();
                        String pesoStr = valore[i + 1].trim();

                        if (!Libreria.isInteger(pesoStr)) {
                            throw new ExeException(this, "caricaGraficDaFile - peso non intero", "Valore non Numerico : " + pesoStr);
                        }
                        int peso = Integer.parseInt(pesoStr);

                        if (!esisteChiave(destinazione)) {
                            addPunto(destinazione , true);  // <- Aggiungi destinazione se non esiste
                        }

                        // Costruisci mappa collegamento
                        Map<String, Integer> collegamentoA = new HashMap<>();
                        collegamentoA.put(destinazione, peso);
                        addColegamento(puntoID, collegamentoA);

                        Map<String, Integer> collegamentoB = new HashMap<>();
                        collegamentoB.put(puntoID, peso);
                        addColegamento(destinazione, collegamentoB);
                    }//end for - addColegamento
                } else {
                    for (int i = 1; i < valore.length; i++) {
                        String destinazione = valore[i].trim();
                        graficoDati.putIfAbsent(destinazione, new HashMap<>());

                        Map<String, Integer> collegamentoA = new HashMap<>();
                        collegamentoA.put(destinazione, 1);
                        addColegamento(puntoID, collegamentoA);

                        Map<String, Integer> collegamentoB = new HashMap<>();
                        collegamentoB.put(puntoID, 1);
                        addColegamento(destinazione, collegamentoB);
                    }//end for - addColegamento
                }//end if - grafico Con Peso

            }//while - cicolo per Riga File

            controllaGrafico();

        } catch (Exception e) {
            throw new ExeException(this , "caricaGraficDaFile - Errore Lettura File" , e.getMessage());
        }
    }//caricaDaFile

    public Set<String> getPunti(){
        return graficoDati.keySet();
    }

    @Override
    public String toString() {
        StringBuilder strRetrun = new StringBuilder();
        strRetrun.append("-------------------------\n");
        strRetrun.append("Nome del Grafico : "+nomeGrafico+"\n");
        strRetrun.append("Grafico "+ (isGraficContienePeso ? "con" : "seza") +" peso\ng");

        for (Map.Entry<String, Map<String, Integer>> entry : graficoDati.entrySet()) {
            String punto = entry.getKey();
            Map<String, Integer> collegamenti = entry.getValue();

            strRetrun.append(punto).append(" -> ");

            if (collegamenti.isEmpty()) {
                strRetrun.append("{}");
            } else {
                strRetrun.append("{");
                int count = 0;
                for (Map.Entry<String, Integer> collegamento : collegamenti.entrySet()) {
                    strRetrun.append(collegamento.getKey()).append("=").append(collegamento.getValue());
                    if (++count < collegamenti.size()) {
                        strRetrun.append(", ");
                    }
                }
                strRetrun.append("}\n");
            }

        }
        strRetrun.append("-------------------------");
        return strRetrun.toString();
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
