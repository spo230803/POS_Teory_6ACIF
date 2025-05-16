package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class GraDiametro extends CMDBase {
    public GraDiametro(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        
    }//avvio

    @Override
    public void help() {
        System.out.println("Calcoal il Diametro da un Grafic");
    }//help
}//GraDiametro
