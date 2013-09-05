package l20n.compiler;

import l20n.exceptions.L20nException;

public class SyntaxException extends L20nException {

    private static final long serialVersionUID = 7132913312275015853L;

    public SyntaxException() {
        // TODO Auto-generated constructor stub
    }

    public SyntaxException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public SyntaxException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SyntaxException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

}
