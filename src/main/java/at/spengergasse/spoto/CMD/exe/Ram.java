package at.spengergasse.spoto.CMD.exe;

import at.spengergasse.spoto.Libreria.CMDBase;
import at.spengergasse.spoto.Terminale;

public class Ram extends CMDBase {
    public Ram(Terminale terminal) {
        super(terminal);
    }

    @Override
    public void avvio() {
        Runtime runtime = Runtime.getRuntime();

        // Richiama il garbage collector (opzionale, ma aiuta ad avere un dato più pulito)
        runtime.gc();

        long memoriaTotale = runtime.totalMemory();     // Memoria totale allocata alla JVM
        long memoriaLibera = runtime.freeMemory();      // Memoria libera dentro la JVM
        long memoriaInUso = memoriaTotale - memoriaLibera;

        System.out.println("Memoria totale allocata: " + memoriaTotale / (1024 * 1024) + " MB");
        System.out.println("Memoria libera: " + memoriaLibera / (1024 * 1024) + " MB");
        System.out.println("Memoria in uso: " + memoriaInUso / (1024 * 1024) + " MB");
    }

    @Override
    public void help() {
        System.out.println("Visualizza lo stato della Memora attualmente in Uso");
    }
}//ram
