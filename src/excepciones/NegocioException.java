
package excepciones;

public class NegocioException extends Exception {

    public NegocioException() {
    }

    public NegocioException(String string) {
        super(string);
    }

    public NegocioException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NegocioException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NegocioException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
}
