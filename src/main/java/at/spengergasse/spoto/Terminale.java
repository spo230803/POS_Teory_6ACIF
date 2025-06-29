/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    Gestione del Terminale
 */


package at.spengergasse.spoto;

import at.spengergasse.spoto.CMD.grafico.*;
import at.spengergasse.spoto.CMD.matrici.*;
import at.spengergasse.spoto.CMD.exe.*;
import at.spengergasse.spoto.Libreria.*;
import at.spengergasse.spoto.CMD.punti.*;

import at.spengergasse.spoto.Libreria.exception.ExeException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service; //Crea un solo Oggetto per TUTTO Il programma

import java.util.*;

import static at.spengergasse.spoto.Main.SETTIING_HELP_CMD;

@Getter
@Setter
@Service
public class Terminale {

    //Variabili Salvate a livello Globale
    private String produtKeyUser;
    private String nomeUtente;
    private String separaValoreMarice=";";
    private int countRisutlato = 0;

    private Map<String , Class<? extends CMDBase>> mappaComandi =new HashMap(); //Memorizzo la CLASSE al posto di un Oggeto
    private Map<String , VarMatrice> poolMatrici =new HashMap();
    private Map<String , VarGrafico> poolGrafico =new HashMap();
    private Map<String , VarPunti> poolPunti =new HashMap();
    private ArrayList<String> cronologiaComandi = new ArrayList<>();


    public Terminale() {
        //Elenco di Funzioni

        //Exe
        mappaComandi.put("NOME_UTENTE" , NomeUtente.class );
        mappaComandi.put("EXIT", Exit.class);
        mappaComandi.put("HELP" , Help.class );
        mappaComandi.put("SET_SEPARA_VALORE", SetSeparaValore.class);
        mappaComandi.put("PK", PK.class);
        mappaComandi.put("VER" , Ver.class);
        mappaComandi.put("DEBUG", Debug.class);
        mappaComandi.put("RUN", Run.class);
        mappaComandi.put("RAM", Ram.class);
        mappaComandi.put("STORIA", Storia.class);

        //Gestione Grafici
        mappaComandi.put("GRA_LIST", GrafList.class);
        mappaComandi.put("GRA_ADD", GrafAdd.class);
        mappaComandi.put("GRA_ADD_MANUAL", GrafAddManual.class);
        mappaComandi.put("GRA_DELETE", GrafDelete.class);
        mappaComandi.put("GRA_PRINT", GrafPrint.class);
        mappaComandi.put("GRA_CALC_DIST", GrafCaclolaDistanza.class);
        mappaComandi.put("GRA_EXZENTR", GrafEscentricita.class);
        mappaComandi.put("GRA_RADIUS", GrafRaggio.class);
        mappaComandi.put("GRA_DURCHMESSER", GrafDiametro.class);
        mappaComandi.put("GRA_ZENTRUM", GrafCentro.class);
        mappaComandi.put("GRA_KOMPONENTEN", GrafComponenti.class);
        mappaComandi.put("GRA_DFS", GrafDFS.class);
        mappaComandi.put("GRA_ARTI", GrafAricolazioni.class);
        mappaComandi.put("GRA_PONTI",  GrafPonti.class);
        mappaComandi.put("GRA_CALC_DIST_FW", GrafCaclolaDistanzaFW.class);

        //Gestione Matrici
        mappaComandi.put("MAT_ADD" , MatAdd.class );
        mappaComandi.put("MAT_PRINT", MatPrint.class);
        mappaComandi.put("MAT_ADD_MANUAL" , MatAddManual.class);
        mappaComandi.put("MAT_DELETE" , MatDelete.class);
        mappaComandi.put("MAT_LIST" , MatList.class );
        mappaComandi.put("MAT_CREATE_GRAFICO" , MatCreaGrafico.class);


        //Gestione Punti
        mappaComandi.put("PUNT_PRINT", PuntPrint.class);
        mappaComandi.put("PUNT_DELETE", PuntDelete.class);
        mappaComandi.put("PUNT_LIST", PuntList.class);

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

        inputUtente = inputUtente.toUpperCase().trim();

        if(inputUtente.equals("STOP")){
            //Uscita di Emmergenza
            System.out.println("Arresto d'Emmergenza del Programma in corso ...");
            ExeException e = new ExeException(this , "terminaleGetInput" , "Uscita d'Emmergenza del Programma" );
            System.exit(-1);
        }
        return inputUtente;
    }//terminaleGetInput

    public void cancellaConsole(){

        //TODO: da migliorare
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
           cronologiaComandi.add(comando);
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

    public Integer getCountRisutlato(){
        countRisutlato++;
        return countRisutlato;
    }

    public String getNomeRisultato(){
        Integer countRisutlato = getCountRisutlato();
        return "R"+countRisutlato.toString();
    }

    public void addPoolMatrici(String nomeMatrice, VarMatrice clone) {
        this.poolMatrici.put(nomeMatrice, clone);
    }
    public void addPoolGrafico(String nomeGrafico, VarGrafico clone) {
        this.poolGrafico.put(nomeGrafico, clone);
    }
    public void addPoolPunit(String nomePunit, VarPunti clone) {
        this.poolPunti.put(nomePunit, clone);
    }
}//Executive
