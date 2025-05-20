/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Aggiunta di una matrice manuale
 */

package at.spengergasse.spoto.CMD.matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Libreria.Libreria;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

import static at.spengergasse.spoto.Main.SETTIING_MAX_MATRICE;

public class MatAddManual extends CMDBase {
    public MatAddManual(Terminale terminal) {
        super(terminal);
    }

    //vbgio
    private VarMatrice nuovaMatrice;

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        String nomeMatrice = super.inputString("Nome della Matrice");
        System.out.println("Nome della Matrice: " + nomeMatrice);
        int numRighe = numeroRighe();
        int numColonna = numRighe;
        nuovaMatrice = new VarMatrice(nomeMatrice);

        for(int r = 0; r < numRighe; r++){
            nuovaMatrice.createRiga();
            for(int c = 0; c < numColonna; c++){
                inputValore(r,c);
            }
        }//for righe

        terminal.addPoolMatrici(nomeMatrice , nuovaMatrice.clone());
        System.out.println("Matrice salvata : "+nomeMatrice);
    }//avvio


    ///  -----------------------------------------  Inserimento del Input   --------------------------------

    private int numeroRighe() {
        String numRighe = terminal.terminaleGetInput("Numero righe");

            int num = Integer.parseInt(numRighe);
            if(num < 2 || num > SETTIING_MAX_MATRICE){
                System.out.println("ERRORE : nummero righe deve essre tra 2 e "+SETTIING_MAX_MATRICE);
                numeroRighe();
            }
        return Integer.parseInt(numRighe);
    }//numeroRighe


    private void inputValore(int r , int c){
        String val = terminal.terminaleGetInput("Inserire il valore per [ "+r+" , "+c+" ] ");
        if(val == null || val.equals("") || !Libreria.isInteger(val)) {inputValore(r,c);}

        //Salvataggio nella Matrice
        try {
            nuovaMatrice.addValore(r, Integer.parseInt(val));
        }catch (ExeException e) {
            System.out.println("Errore Input [ "+r+" , "+c+" ] : " + e.getMessage());
            inputValore(r,c);
        }

    }//inputValore


    @Override
    public void help() {
        System.out.println("Agginuta manuale di una matrice");
        System.out.println("Input richiesti : Nome Matrice, quante Righe e Valori");
    }//help
}//AddManual
