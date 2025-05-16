/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Classe per la memorizazione della Matrice
 */


package at.spengergasse.spoto.Libreria;

import at.spengergasse.spoto.Terminale;
import lombok.Getter;
import lombok.Setter;


import java.io.*;
import java.util.ArrayList;
import java.util.Set;

import static at.spengergasse.spoto.Libreria.Libreria.isInteger;

@Setter
@Getter

public class VarMatrice extends VarBase{

    private String fileName; //Origine della Matrice
    private ArrayList<ArrayList<Integer>> matriceDati = new ArrayList<>(); //Array multivettoriale [R - Riga][C - Colonna]
    private String matriceNome;
    private Terminale terminale;
    private VarGrafico graficDati;


    //Costruttori
    public VarMatrice() {}
    public VarMatrice(String matriceNome) {
        setMatriceNome(matriceNome);
    }
    public VarMatrice(Terminale terminal) {
        setTerminale(terminal);
    }
    public VarMatrice(Terminale terminale, String matriceNome) {
        setTerminale(terminale);
        setMatriceNome(matriceNome);
    }



    public void caricaMatriceDaFile(String fileName , String matriceNome) throws ExeException {
        //Funzione che dato un File, Carica la amtrice in essa

        //Variabli Locali
        int conutRiga = 0;

        //Controllo Parametri
        if(matriceNome == null || matriceNome.equals("")){
            throw new ExeException(this ,"caricaMatriceDaFile - par matriceName" , "Nome Matrice non puo essere vuoto");
        }
        if(fileName == null || fileName.equals("")){
            throw new ExeException(this , "caricaMatriceDaFile - par fileName" , "File Input Matrice non puo essere vuoto");
        }

        //Controlo sul File
        File matriceFile = new File(fileName);
        if(!matriceFile.exists()){
            throw new ExeException(this, "caricaMatriceDaFile - par fileName"  , "File non trovato ("+fileName+")");
        }
        if(!matriceFile.isFile()){
            throw new ExeException(this,"caricaMatriceDaFile - par fileName"  , "Non è un File! ("+fileName+")" );
        }

        //Leggo il File
        try {
            FileReader matriceFileReader = new FileReader(fileName);
            BufferedReader matriceBufferedReader = new BufferedReader(matriceFileReader);
            String linea;
            while((linea = matriceBufferedReader.readLine()) != null){
                linea  = linea.trim();
                if(linea.equals("")){ continue;} //Salto le line Vuote

                //Leggo il File Riga per Riga
                String[] valore = linea.split(terminale.getSeparaValoreMarice());
                ArrayList<Integer> riga = new ArrayList<>();
                for(String val : valore){
                    val = val.trim();
                    //Cicolo per ogni valore per riga
                    if(isInteger(val) ){
                        riga.add(Integer.parseInt(val));
                    }else {
                        throw new ExeException(this, "caricaMatriceDaFile" , "La Matrice ha un valore non valido : "+val );
                    }
                }//end For - per Valore
                matriceDati.add(riga);
            }//while - per Riga
        }catch (Exception e){
            throw new ExeException(this , "caricaMatriceDaFile - Errore Lettura File" , e.getMessage());
        }

        //Verifico che ogni Riga abbia gli stessi valori
        int countValoreOra = -1;
        for(ArrayList<Integer> riga : matriceDati){
            if(countValoreOra == -1){
                countValoreOra = riga.size();
            }else {
                if(countValoreOra != riga.size()){
                    throw new ExeException(this, "caricaMatriceDaFile", "La Matrice non ha per riga gli stesi valori!");
                }//fine controllo valori
            }//end if - Fine prima riga
        }//for - per riga
    }//caricaMatriceDaFile


    @Override
    public VarMatrice clone() {
        //Clono questa matrice
        try {
            return (VarMatrice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai accadere
        }
    }


    public int getValore(int x , int y) throws ExeException {
        try{
            return matriceDati.get(x).get(y);
        } catch (Exception e) {
            throw new ExeException(this , "getValore x="+x+" y="+y, e.getMessage() );
        }
    }//getValore

    public void setValore(int r , int c, int valore) throws ExeException {
        try {
            matriceDati.get(r).set(c, valore);
        } catch (Exception e) {
            throw new ExeException(this, "setValore r=" + r + " c=" + c + " valore=" + valore, e.getMessage());
        }
    }//setValore

    public void addValore(int r , int valore) throws ExeException {
        if(matriceDati.get(r) == null){
            createRiga();
        }
        try {
            matriceDati.get(r).add(valore);
        } catch (RuntimeException e) {
            throw new ExeException(this, "setValore x=" + r + "  valore=" + valore, e.getMessage());
        }
    }//addValore


    public void createRiga(){
        ArrayList<Integer> riga = new ArrayList<>();
        matriceDati.add(riga);
    }//createRiga

    public void setMariceVuota(int numRige , int numColonne , int valDefault ) throws ExeException {
        for(int r = 0 ; r < numRige ; r++){
            createRiga();
            for(int c = 0 ; c < numColonne ; c++){
                addValore(r , valDefault);
            }//for - colonne
        }//end for - righe
    }//setMariceVuota

    @Override
    public String toString()   {
        StringBuilder strRetrun = new StringBuilder();

        strRetrun.append("--------------------");
        strRetrun.append("\n");
        strRetrun.append("Matrice Nome : " + getMatriceNome() + "\n");

        if(graficDati == null) { //la matrice non dervia da un Grafico
            strRetrun.append("(Matrice non da Grafico)\n");
            for (ArrayList<Integer> riga : matriceDati) {
                for (Integer i : riga) {
                    strRetrun.append(i).append("\t");
                }
                strRetrun.append("\n");
            }
        }else {
            strRetrun.append("Matrice dal Grafico "+graficDati.getNomeGrafico()+"\n");
            String[] listaPunti =  graficDati.getPunti().toArray(new String[0]);

            //Stampa la intestazione in alto
            for(int i = 0 ; i < listaPunti.length ; i++){
                if(i==0){strRetrun.append("\t");}
                strRetrun.append(listaPunti[i]).append("\t");
            }
            strRetrun.append("\n");
            strRetrun.append("______________________");
            strRetrun.append("\n");

            for(int i = 0 ; i < matriceDati.size() ; i++){
                strRetrun.append(listaPunti[i]).append("|").append("\t"); //Stampa intestazione per ogni riga
                for (Integer v : matriceDati.get(i)) {
                    strRetrun.append(v).append("\t");
                }
                strRetrun.append("\n");
            }
        }//end if
        strRetrun.append("--------------------");

        return strRetrun.toString();
    }//toString
}//VarMatrice
