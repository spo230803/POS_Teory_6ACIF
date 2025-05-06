package at.spengergasse.spoto.Libreria;

import lombok.Getter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static at.spengergasse.spoto.Main.SETING_FILE_LOG;
import static at.spengergasse.spoto.Main.SETING_SEPARA_VALORE;

@Getter
public class ExeException extends Exception {

    //Variabili Istanza
    private String zona = null;

    public ExeException(String message, String zona) {
        super(message);
        setZona( zona);
    }//Costruttore


    private void salvaLog(){
        FileWriter fw;
        BufferedWriter bw;
        LocalDateTime ora = LocalDateTime.now();
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String oraString = ora.format(dataFormat);

        try{
            fw = new FileWriter( SETING_FILE_LOG, true); // Ture = Scrivi alla fine file FALSE = Scrivi dal inzio (Overwhrite)
            bw = new BufferedWriter(fw);
            bw.write( oraString + SETING_SEPARA_VALORE + getZona() + SETING_SEPARA_VALORE + getMessage()  );
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } //try
    }

    private void setZona(String zona) {
        //setZona = non deve essere pubblico
        this.zona = zona;
    }
}//ExeException
