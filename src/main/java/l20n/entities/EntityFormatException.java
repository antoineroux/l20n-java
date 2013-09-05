package l20n.entities;

import l20n.exceptions.L20nException;

public class EntityFormatException extends L20nException {

    private static final long serialVersionUID = 447158210278056165L;

    public EntityFormatException() {
        // TODO Auto-generated constructor stub
    }


    public EntityFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityFormatException(String message) {
        super(message);
    }
}
