/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06
 */

package at.spengergasse.spoto.Libreria;

import at.spengergasse.spoto.Terminale;

import static at.spengergasse.spoto.Main.PRODUT_KEY;

public class ClasseBase {

    protected Terminale terminal;

    public ClasseBase(Terminale terminal) {
        this.terminal = terminal;
    }

    public String inputString(String msg){
        String nome = terminal.terminaleGetInput(msg);
        if(nome == null || nome.equals("")){
            System.out.print("! Input non valido ! \t");
            return inputString(msg);
        }
        else{ return nome;}
    }//inputNome

    public Integer inputInteger(String msg){
        String num = terminal.terminaleGetInput(msg);
        if(num == null || num.equals("") || !Libreria.isInteger(num)){
            System.out.print("! Input non valido ! \t");
            return inputInteger(msg);
        }
        else{ return Integer.parseInt(num);}
    }//inputInteger

    public boolean inputBoolean(String msg){
        String bool = inputString(msg +"\t Y or N");
        if(bool.equals("Y")){
            return true;
        }else if(bool.equals("N")){
            return false;
        }else {
            System.out.print("! Input non valido ! \t");
            return inputBoolean(msg);
        }
    }

    public boolean controllaPK(){

        //Vbgio - implemntare Cripto by MD5
        return true;

        /*
        if(terminal.getProdutKeyUser() == null || terminal.getProdutKeyUser().isEmpty() ){
            System.out.println("Produt Key non inserito! Impossible avviare il Programma!!!");
            System.out.println("Per risolvere il problema : inserire il Produk Key con il comando PK");
            return false;
        }
        if(PRODUT_KEY.equals(terminal.getProdutKeyUser())){
            return true;
        }
        System.out.println("Produt key inserito non è valido per questo Programma!");
        return false;
         */
    }//controllaPK

}//ClasseBase
