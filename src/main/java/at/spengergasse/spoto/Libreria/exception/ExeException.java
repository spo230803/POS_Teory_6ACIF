/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Grestore delle Eccezioni personalizato da Me
 */

package at.spengergasse.spoto.Libreria.exception;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static at.spengergasse.spoto.Main.SETING_FILE_LOG;
import static at.spengergasse.spoto.Main.SETING_SEPARA_VALORE;

@Getter
public class ExeException extends Exception {

    //COSTANTI
    private final String INGOTO = "-??-";

    //Variabili Istanza
    private String zona = INGOTO;
    private String oggetto = INGOTO;



    public ExeException(String zona, String message) {
        super(message);
        setZona( zona);
        salvaLog();
    }//Costruttore

    public ExeException(Object oggetto , String zona , String message) {
        super(message);
        setZona( zona);
        setOggetto( oggetto);
        salvaLog();
    }//Costruttore 2
    public ExeException(Object oggetto , String message) {
        super(message);
        setOggetto( oggetto);
    }

    @Override
    public String getMessage() {
        if(super.getMessage() == null){
            return "!! SYSTEM ERROR !! \n Messaggio Exceptio non trovato!";
        } else if (super.getMessage().startsWith("Si è verificato un Errore : ")) {
            return super.getMessage();
        }else {
            return "Si è verificato un Errore : " + super.getMessage();
        }
    }//getMessate


    private void salvaLog(){
        //Formato Log = DATA # Oggetto # Zona # Messaggio

        FileWriter fw;
        BufferedWriter bw;
        LocalDateTime ora = LocalDateTime.now();
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String oraString = ora.format(dataFormat);

        try{
            fw = new FileWriter( SETING_FILE_LOG, true); // Ture = Scrivi alla fine file FALSE = Scrivi dal inzio (Overwhrite)
            bw = new BufferedWriter(fw);
            bw.write( oraString + SETING_SEPARA_VALORE + getOggetto() + SETING_SEPARA_VALORE + getZona() + SETING_SEPARA_VALORE + getMessage()  );
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } //try
    }

    private void setZona(String zona) {
        //non deve essere pubblico (Per forzare il passaggio dal costruttore
        if(zona == null || zona.isEmpty()){zona = INGOTO;}
        this.zona = zona;
    }
    private void setOggetto(Object oggetto) {
        this.oggetto = (oggetto == null) ? INGOTO : oggetto.getClass().getName();
    }

    @Override
    public String toString() {
        return "Errore nel Programma: " + super.getMessage();
    }
}//ExeException
