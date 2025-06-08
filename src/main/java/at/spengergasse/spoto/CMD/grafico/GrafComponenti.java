package at.spengergasse.spoto.CMD.grafico;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.VarPunti;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Setter
@Getter
public class GrafComponenti extends CMDBase {

    //Variabili
    private String returnVarPunti;
    private VarPunti retrunPunti;

    public GrafComponenti(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        String nomeGrafcio = super.inputString("Nome grafcio: ");
        try {
            returnVarPunti = calcola(nomeGrafcio);
            retrunPunti = terminal.getPoolPunti().get(returnVarPunti).clone();
            System.out.println(retrunPunti);
        } catch (ExeException e) {
            System.out.println(e);
        }
    }//avvio

    public String calcola(String nomeGrafico) throws ExeException {

        //Varaibili Locali
        String returnNomePunti = super.inputString("Nome return Variabie Punti : ");
        VarPunti returnPunti = new VarPunti(terminal , returnNomePunti);

        try {
            super.controllaEsisteGrafico(nomeGrafico);
        }catch (ExeException e) {
            throw e;
        }


        terminal.addPoolPunit(returnNomePunti , returnPunti);
        return returnNomePunti;
    }//calcola

    private void trovaComponentiConnesse(VarGrafico grafico) throws ExeException {
        int numeroPunti = grafico.getPunti().size();
        boolean[] puntiVisitati = new boolean[numeroPunti];

        //TODO: da verificare
        List<List<Integer>> componenti = new ArrayList<>();

        for(int i = 0 ; i < numeroPunti ; i++) {
            if(!puntiVisitati[i]) {

            }//end if - Punto non Visuistato
        }//end for - Cicolo per punti
    }//trovaComponentiConnesse

    private void ricorsivo_DFS() throws ExeException {
        //Depth-First Search



    }//dfs

    @Override
    public void help() {
        System.out.println("Cacolo da un Grafico componenti connesse");
    }
}//GraComponenti
