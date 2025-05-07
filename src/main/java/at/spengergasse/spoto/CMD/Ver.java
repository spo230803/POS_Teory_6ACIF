package at.spengergasse.spoto.CMD;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

import static at.spengergasse.spoto.Main.*;

public class Ver extends CMDBase {

    public Ver(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        System.out.println("------------------------");
        System.out.println("Nome Programma :\t"+ APP_NAME);
        System.out.println("Versione : \t\t\t" + APP_VERSION);
        System.out.println("Del : \t\t\t\t"+ APP_UPDATE);
        System.out.println("Autore : \t\t\t" + AUTORE);
        System.out.println("Classe : \t\t\t" + CLASSE);
        System.out.println("------------------------");
    }//avvio

    @Override
    public void help() {
        System.out.println("Informazioni sul Programma");
        avvio();
    }//help
}//Ver
