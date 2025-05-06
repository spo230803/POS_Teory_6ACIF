package at.spengergasse.spoto;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    //Costanti Globali
    public static final String AUTORE = "SPOTO Giorgio Matteo";
    public static final String CLASSE = "6A CIF";
    public static final String APP_NAME = "POS Teora Projekt";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_UPDATE = "-none-";


    //IMPOSTAZIONI
    public static final String SETING_SEPARA_VALORE = "#";
    public static final String SETING_FILE_LOG = "log.txt";

    public static void main(String[] args) {
        Executive exe = new Executive();

        System.out.println("Benvenuti in "+ APP_NAME);
        System.out.print("Versione " + APP_VERSION + " del " + APP_UPDATE);
        System.out.println("--------------------------------------------");
        exe.avvioProgramma();
    }
}