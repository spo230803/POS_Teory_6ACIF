/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Gestione del Terminale
 */


package at.spengergasse.spoto;

import at.spengergasse.spoto.CMD.*;
import at.spengergasse.spoto.CMD.Matrici.*;
import at.spengergasse.spoto.Libreria.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service; //Crea un solo Oggetto per TUTTO Il programma
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static at.spengergasse.spoto.Main.SETTIING_HELP_CMD;

@Getter
@Setter
@Service
public class Terminale {

    //Variabili Salvate a livello Globale
    private String produtKeyUser;
    private String nomeUtente;
    private String separaValoreMarice=";";

    private Map<String , Class<? extends CMDBase>> mappaComandi =new HashMap();
    private Map<String , VarMatrice> mappaMatrici =new HashMap();


    public Terminale() {
        //Elenco di Funzioni
        mappaComandi.put("NOME_UTENTE" , NomeUtente.class );
        mappaComandi.put("EXIT", Exit.class);
        mappaComandi.put("HELP" , Help.class );
        mappaComandi.put("LISTA" , Lista.class );
        mappaComandi.put("ADD" , Add.class );
        mappaComandi.put("SET_SEPARA_MATRICE", SetSeparaValore.class);
        mappaComandi.put("PK", PK.class);
        mappaComandi.put("PRINT", Print.class);
        mappaComandi.put("ADD_MANUAL" , AddManual.class);
        mappaComandi.put("VER" , Ver.class);
    }//Terminale

    public void avvioProgramma(){
        NomeUtente nomeUtente = new NomeUtente(this);
        nomeUtente.avvio();
        System.out.println("Per avere supporto scrivi il comando HELP");
        cmd();
    }//avvioProgramma

    public String terminaleGetInput(String msg){
        if(msg != null && msg.length() > 0){
            System.out.println(msg+" : ");
        }
        Scanner scanner = new Scanner(System.in); // Creazione di un oggetto Scanner per leggere input
        String inputUtente = scanner.nextLine();
        //scanner.close();
        return inputUtente.toUpperCase().trim();
    }//terminaleGetInput

    public void cancellaConsole(){

        //VBGIO da migliorare
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void cmd(){
        //Metodo per esecuzione dei Comandi
        String comando = terminaleGetInput("Inserire un Comando");
        if(comando == null || comando.length() == 0){
            //Se non esiste il comando lo richiedo di nuovo
            cmd();
            return;
        }

        Boolean siamoInHelp = (comando.indexOf(SETTIING_HELP_CMD) == -1 ? false : true);
        if(siamoInHelp){
            //Prendo solo il nome del comando
            int posizioneHelp = comando.indexOf(SETTIING_HELP_CMD);
            comando=  comando.substring(0,posizioneHelp).trim();
        }

       if(mappaComandi.containsKey(comando)){
            //Comando Trovato
           try {
               CMDBase oggComando = mappaComandi.get(comando)
                       .getDeclaredConstructor(Terminale.class)
                       .newInstance(this);
               if(siamoInHelp){
                   oggComando.help();
               }else {
                   oggComando.avvio();
               }
           }catch (Exception e){
               System.out.println(e.getMessage());
           }
       }else {
           System.out.println("Comando non trovato: " + comando);
           System.out.println("Usa il comando HELP per l'elendo dei comandi");
       }//end if - controllo esistenza comando
        cmd(); //Alla fine richiedo di nuovo un comando
    }//cmd


    public void setMappaMatrici(String nomeMatrice, VarMatrice clone) {
        this.mappaMatrici.put(nomeMatrice, clone);
    }
}//Executive
