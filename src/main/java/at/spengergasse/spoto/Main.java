/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06
 */

package at.spengergasse.spoto;


public class Main {

    //Costanti Globali
    public static final String AUTORE = "SPOTO Giorgio Matteo";
    public static final String CLASSE = "6A CIF";
    public static final String APP_NAME = "POS Teora Projekt";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_UPDATE = "2025-05-06";

    public static final String PRODUT_KEY = "X";

    //IMPOSTAZIONI
    public static final String SETING_SEPARA_VALORE = "#";
    public static final String SETING_FILE_LOG = "log.txt";
    public static final String SETTIING_HELP_CMD = "?";
    public static final int SETTIING_MAX_MATRICE = 30;


    public static void main(String[] args) {
        Terminale exe = new Terminale();

        System.out.println("Benvenuti in "+ APP_NAME);
        System.out.println("Versione " + APP_VERSION + " del " + APP_UPDATE);
        System.out.println("--------------------------------------------");
        exe.avvioProgramma();
    }//main
}//Main