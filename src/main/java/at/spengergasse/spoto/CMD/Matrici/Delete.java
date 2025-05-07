package at.spengergasse.spoto.CMD.Matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Terminale;

import java.util.Map;

public class Delete extends CMDBase {

    public Delete(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        if(!super.controllaPK()){return;}

        String nomeMatrice = terminal.terminaleGetInput("Nome della Matrice da cancellare");
        VarMatrice printMatrice ;

        if(nomeMatrice == null || nomeMatrice.equals("")){
            avvio();
            return;
        }

        Map matrici = terminal.getMappaMatrici();
        matrici.remove(nomeMatrice);
    }//avvio

    @Override
    public void help() {
        System.out.println("Cancella una Matrice dalla memoria");
    }
}//Delte
