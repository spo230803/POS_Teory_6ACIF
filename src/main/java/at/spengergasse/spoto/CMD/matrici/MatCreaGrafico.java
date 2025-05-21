package at.spengergasse.spoto.CMD.matrici;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Libreria.VarGrafico;
import at.spengergasse.spoto.Libreria.VarMatrice;
import at.spengergasse.spoto.Libreria.exception.ExeException;
import at.spengergasse.spoto.Terminale;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MatCreaGrafico extends CMDBase {

    private List<Character> lettere = new ArrayList<>();

    public MatCreaGrafico(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {

        //Variabili Locali
        String nomeMatrice = super.inputString("Nome Matrice: ");
        String nomeGrafico;

        try {
            nomeGrafico = carica(nomeMatrice);
        } catch (ExeException e) {
            System.out.println(e);
            return;
        }

        System.out.println("Grafico caricato con successo (Grafico : "+nomeGrafico+") ");
        System.out.println(terminal.getPoolGrafico().get(nomeGrafico));
    }//avvio

    public String carica(String nomeMatrice) throws ExeException {

        //Varaibli Locali
        String nomeGrafico = terminal.getNomeRisultato();
        VarMatrice matriceOrigne;
        VarGrafico graficoDestinazione = new VarGrafico(terminal , nomeGrafico);
        int dimeMatrx;
        Map<String , Map<String , Integer>> datiTemp = new HashMap<>();
        boolean isGraficoConPeso = false;


        //Finalizazione prima dei calcoli
        for (char c = 'A'; c <= 'Z'; c++) {
            lettere.add(c);
        }
        try{
            super.controllaEsisteMatrix(nomeMatrice);
        } catch (ExeException e) {
            throw e;
        }
        matriceOrigne = terminal.getPoolMatrici().get(nomeMatrice).clone();
        dimeMatrx = matriceOrigne.getDimeMatrice();

        //Ciclo sulla Matrice per ottenre il grafico
        try {
            for (int x = 0; x < dimeMatrx; x++) {
                //Aggiungo punti
                datiTemp.put(lettere.get(x).toString(), null);
                Map<String , Integer> colegamenti = new HashMap<>();
                for (int y = 0; y < dimeMatrx; y++) {
                    int peso = matriceOrigne.getValore(x, y);
                    if(peso != 0){
                        if(peso > 1 ){ isGraficoConPeso = true; }
                        else { isGraficoConPeso = false; }
                        colegamenti.put(lettere.get(y).toString(), peso);
                    }
                }//for Y
                datiTemp.put(lettere.get(x).toString(), colegamenti);
            }//for X

            //Salvo nel Grafico
            graficoDestinazione.setGraficoDati(datiTemp);
            graficoDestinazione.setGraficContienePeso(isGraficoConPeso);
        }catch (ExeException e) {
            System.out.println(e);
        }

        terminal.addPoolGrafico(nomeGrafico, graficoDestinazione.clone());
        return nomeGrafico;
    }//carica


    @Override
    public void help() {
        System.out.println("Da una Matrice in memoria genera un grafico");
    }//help
}//MatCreaGrafico
