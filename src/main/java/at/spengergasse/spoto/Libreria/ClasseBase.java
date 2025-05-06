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

    public boolean controllaPK(){
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
    }//controllaPK
}//ClasseBase
