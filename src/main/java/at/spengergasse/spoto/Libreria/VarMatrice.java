/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06
 */


package at.spengergasse.spoto.Libreria;

import at.spengergasse.spoto.Terminale;
import lombok.Getter;
import lombok.Setter;


import java.io.*;
import java.util.ArrayList;

@Setter
@Getter
public class VarMatrice implements Cloneable{

    private String fileName; //Origine della Matrice
    private ArrayList<ArrayList<Integer>> matriceDati; //Array multivettoriale [][]
    private String matriceNome;
    private Terminale terminale;



    public VarMatrice(String matriceNome) {
        setMatriceNome(matriceNome);
    }
    public VarMatrice() {}
    public VarMatrice(Terminale terminal) {
        setTerminale(terminal);
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

    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // La stringa è un numero intero
        } catch (NumberFormatException e) {
            return false; // La stringa non è un numero intero
        }
    }

    public int getValore(int x , int y) throws ExeException {
        try{
            return matriceDati.get(x).get(y);
        } catch (Exception e) {
            throw new ExeException(this , "getValore x="+x+" y="+y, e.getMessage() );
        }
    }//getValore

    public void setValore(int x , int y, int valore) throws ExeException {
        try {
            matriceDati.get(x).set(y, valore);
        } catch (Exception e) {
            throw new ExeException(this, "setValore x=" + x + " y=" + y + " valore=" + valore, e.getMessage());
        }
    }//setValore

    @Override
    public String toString() {
        StringBuilder strRetrun = new StringBuilder();
        for(ArrayList<Integer> riga : matriceDati){
            for(Integer i : riga){
                strRetrun.append(i).append("\t");
            }
            strRetrun.append("\n");
        }
        return strRetrun.toString();
    }
}//VarMatrice
