package l20n.exceptions;

public class L20nException extends RuntimeException {

    private static final long serialVersionUID = 4428037334762481798L;

    public L20nException() {
        super();
    }

    public L20nException(String message, Throwable cause) {
        super(message, cause);
    }

    public L20nException(String message) {
        super(message);
    }

    public L20nException(Throwable cause) {
        super(cause);
    }

}
