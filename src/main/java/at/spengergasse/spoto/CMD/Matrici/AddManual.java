/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Aggiunta di una matrice manuale
 */

package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.Libreria;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;
import org.aspectj.weaver.ast.Var;

public class AddManual extends CMDBase {
    public AddManual(Terminale terminal) {
        super(terminal);
    }

    //vbgio
    private VarMatrice nuovaMatrice;

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        String nomeMatrice = nomeNuovaMatrice();
        int numRighe = numeroRighe();
        int numValori = numeroValori();
        VarMatrice nuovaMatrice = new VarMatrice(nomeMatrice);

        for(int r = 0; r < numRighe; r++){
            for(int v = 0; v < numValori; v++){

                nuovaMatrice.setValore(r,v, inputValore(r,v));
            }
        }//for righe


    }//avvio


    ///  -----------------------------------------  Inserimento del Input   --------------------------------
    private String nomeNuovaMatrice() {
        String nome = terminal.terminaleGetInput("Nome matrice ");
        if(nome == null || nome.equals("")) {nomeNuovaMatrice();}
        else { return nome;}
        return "";
    }//nomeNuovaMatrice

    private int numeroRighe() {
        String numRighe = terminal.terminaleGetInput("Numero righe");
        if(numRighe == null || numRighe.equals("") || Libreria.isInteger(numRighe)) { numeroRighe();}
        return Integer.parseInt(numRighe);
    }//numeroRighe

    private int numeroValori(){
        String numValori = terminal.terminaleGetInput("Numero valori");
        if(numValori == null || numValori.equals("") || Libreria.isInteger(numValori)) { numeroValori();}
        return Integer.parseInt(numValori);
    }//numeroValori

    private int inputValore(int r , int v){
        String val = terminal.terminaleGetInput("Inserire il valore per [ "+r+" , "+v+" ] ");
        if(val == null || val.equals("") || Libreria.isInteger(val)) {inputValore(r,v);}


        return Integer.parseInt(val);
    }//inputValore


    @Override
    public void help() {
        System.out.println("Agginuta manuale di una matrice");
        System.out.println("Input richiesti : Nome Matrice, quante Righe e Valori");
    }//help
}//AddManual
