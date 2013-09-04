package l20n.exceptions;

public class LolIOException extends L20nException {
    
    private static final long serialVersionUID = -8591989520597501202L;

    public LolIOException(Throwable e) {
        super("An unexpected I/O exception occured while reading the LOL file", e);
    }

}
