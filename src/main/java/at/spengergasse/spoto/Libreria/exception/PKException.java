package at.spengergasse.spoto.Libreria.exception;

public class PKException extends ExeException {
    public PKException(Object o) {
        super(o ,"Produkt Key non Valdio!");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}//PKException
