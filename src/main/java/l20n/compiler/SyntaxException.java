package l20n.compiler;

import l20n.exceptions.L20nException;

public class SyntaxException extends L20nException {

    private static final long serialVersionUID = 7132913312275015853L;

    public SyntaxException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
    }

    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    public SyntaxException(String message) {
        super(message);
    }

    public SyntaxException(Throwable cause) {
        super(cause);
    }

}
