/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Classe base per la Creazione dei Comandi
 */
package at.spengergasse.spoto.Libreria;

import at.spengergasse.spoto.Terminale;

public abstract class CMDBase extends ClasseBase {

    public CMDBase(Terminale terminal) {
        super(terminal);
    }

    public abstract void avvio(); //Avvio del Codice

    public abstract void help(); //Fa vedere un Aiuto

}//CMDBase
