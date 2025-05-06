/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06
 */


package at.spengergasse.spoto.Libreria;

import lombok.Getter;
import lombok.Setter;


import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
public class VarMatrice implements Cloneable{

    private String fileName; //Origine della Matrice
    private ArrayList<ArrayList<Integer>> matriceDati; //Array multivettoriale [][]
    private String matriceNome;


    public VarMatrice(String matriceNome) {
        setMatriceNome(matriceNome);
    }
    public void caricaMatriceDaFile(String fileName , String matriceNome) throws ExeException {
        //Funzione che dato un File, Carica la amtrice in essa


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

        //Inizio a caricare la Matrice


    }//caricaMatriceDaFile

    public VarMatrice() {}

    @Override
    public VarMatrice clone() {
        //Clono questa matrice
        try {
            return (VarMatrice) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // non dovrebbe mai accadere
        }
    }

}//VarMatrice
