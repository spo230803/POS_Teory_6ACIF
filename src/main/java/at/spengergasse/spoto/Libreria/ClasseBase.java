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
        if(terminal.getProdutKeyUser() == null || terminal.getProdutKeyUser().isEmpty() ){return false;}
        if(terminal.getProdutKeyUser().equals(PRODUT_KEY)){return true;}
        return false;
    }//controllaPK
}//ClasseBase
