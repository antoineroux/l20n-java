package l20n;

import java.io.InputStream;

public class Resource {
    
    private final InputStream inputStream;
    
    public Resource(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    
    
}
