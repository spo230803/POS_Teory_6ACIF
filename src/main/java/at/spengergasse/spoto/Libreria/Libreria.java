/*
    Autore  : SPOTO Giorgio
    Classe  : 6A CIF
    Ver     : 1.0.0
    del     : 2025-05-06

    classe conte tutte le funzoni basi e usati frequentemente
 */

package at.spengergasse.spoto.Libreria;

public  class Libreria {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true; // La stringa è un numero intero
        } catch (NumberFormatException e) {
            return false; // La stringa non è un numero intero
        }
    }
}//class
