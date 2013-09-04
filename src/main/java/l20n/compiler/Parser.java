package l20n.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import l20n.compiler.ast.EntityNode;
import l20n.compiler.ast.EntryNode;
import l20n.compiler.ast.HashItemNode;
import l20n.compiler.ast.HashValueNode;
import l20n.compiler.ast.IndexNode;
import l20n.compiler.ast.LolAst;
import l20n.compiler.ast.StringValueNode;
import l20n.compiler.ast.ValueNode;
import l20n.exceptions.LolIOException;
import l20n.exceptions.SyntaxException;

public class Parser {
    
    private PushbackReader isr;
    
    /**
     * The current char being read.
     */
    private char currentChar;
    
    private boolean eofReached = false;
    
    /**
     * Current depth inside a hash. The default is zero (no hash).
     * When a hash is started, it is incremented to 1 (hash level 1). Then, if 
     * there is a nested hash, it is incremented to 2, and so on.
     */
    private int depth = 0;
    
    public Parser(InputStream is) {
        try {
            Reader reader = new InputStreamReader(is, "UTF-8");
            this.isr = new PushbackReader(reader);
        } catch (UnsupportedEncodingException e) {
            // Ignored, as the charset is fixed (UTF-8).
        }
    }
    
    public LolAst parse() {
        LolAst lolAst = new LolAst();
        
        // Swallow any whitespace at the beginning of the file.
        getWs();
        getNextChar();
        unread();
        
        while (!eofReached) {
            EntryNode entry = getEntry();
            lolAst.addEntry(entry);
            getWs();
        }
        
        return lolAst;
    }
    
    /**
     * Swallows any whitespace (" ", \t, \n, \r, \f).
     */
    private void getWs() {
        int readChar;
        do {
            readChar = getNextChar();
        } while(readChar == ' ' || readChar == '\t' || readChar == '\r' || 
                readChar == '\n' || readChar == '\f');
        
        // The last character is not whitespace, make it available.
        unread();
    }
    
    private EntryNode getEntry() {
        int nextChar = getNextChar();
        
        if (nextChar != 60) {
            throw new SyntaxException("An opening entry character '<' was expected");
        }
        
        EntryNode entry = getEntity();
        getWs();
        nextChar = getNextChar();
        
        if (nextChar != 62) {
            throw new SyntaxException("No matching '>' character was found.");
        }
        
        return entry;
    }
    
    
    private EntityNode getEntity() {
        String id = getIdentifier();
        IndexNode index = null;
        getNextChar();
        if (currentChar == '[') {
            index = getIndex();
        } 
        else {
            unread();
        }
        
        getWs();
        
        ValueNode value = getValue();
        
        return new EntityNode(id, index, value);
    }
    
    private String getIdentifier() {
        StringBuilder builder = new StringBuilder();
        
        int nextChar = getNextChar();
        
        // a-zA-Z_
        if ((nextChar < 97 || nextChar > 122) && 
            (nextChar < 65 || nextChar > 90) && nextChar != 95) {
            throw new SyntaxException("Invalid character in identifier: " + 
                                      (char)nextChar + ".");
        }
        
        builder.append((char) nextChar);
        
        nextChar = getNextChar();
        while((nextChar >= 'a' && nextChar <= 'z') || // a-z
              (nextChar >= 'A' && nextChar <= 'Z') ||  // A-Z
              (nextChar >= '0' && nextChar <= '9') ||  // 0-9
              nextChar == '_') /* _ */ {
            
            builder.append((char) nextChar);
            nextChar = getNextChar();
        }
        
        unread();
        return builder.toString();
    }
    
    /**
     * Reads the value from an entity.
     * 
     * @return
     */
    private ValueNode getValue() {
        getNextChar();
        // Single or double quote
        if (currentChar != '\'' && currentChar != '\"' && currentChar != '{') {
            throw new SyntaxException("The value should start with a single " +
            		"or a double quote. Instead, '" + currentChar + 
            		"was found");
        }
        
        if(currentChar == '\'' || currentChar == '\"') {
            return getStringValue(currentChar);
        }
        else {
            return getHashValue();
        }
    }
    
    /**
     * Reads a String value, until a matching quote is found.
     * 
     * @return
     */
    private StringValueNode getStringValue(char openingChar) {
        StringBuilder builder = new StringBuilder();
        
        getNextChar();
        while(currentChar != '\'' && currentChar != '\"') {
            builder.append(currentChar);
            getNextChar();
        }
        StringValueNode valueNode = new StringValueNode(builder.toString());
        return valueNode;
    }
    
    private HashValueNode getHashValue() {
        depth++;
        HashValueNode hashValueNode = new HashValueNode(depth);
        
        getWs();
        
        //getNextChar();
        while(currentChar != '}') {
            getWs();
            boolean isDefault = isMarkedAsDefault();
            String id = getIdentifier();
            getWs();
            getNextChar();
            if (currentChar != ':') {
                throw new SyntaxException("Found " + currentChar + " while ':' was expected.");
            }
            getWs();
            
            ValueNode value = getValue();
            HashItemNode hashItem = new HashItemNode(id, depth, isDefault, value);
            hashValueNode.addHashItem(hashItem);
            
            getWs();
            getNextChar();
            if (currentChar != ',' && currentChar != '}') {
                throw new SyntaxException("Found " + currentChar + " while ',' or '}' was expected.");
            }
        }
        
        // This hash is over, we are now going up the tree.
        depth--;
        return hashValueNode;
    }
    
    private boolean isMarkedAsDefault() {
        getNextChar();
        if (currentChar == '*') {
            return true;
        }
        else {
            unread();
            return false;
        }
    }
    
    private IndexNode getIndex() {
        IndexNode indexNode = new IndexNode();
        
        do {
            getWs();
            getNextChar();
            if(currentChar == '\'' || currentChar == ('\"')) {
                unread();
                StringValueNode valueNode = (StringValueNode) getValue();
                indexNode.addExpression(valueNode.getValue());
                getNextChar();
                if(currentChar != ']' && currentChar != ',') {
                    throw new SyntaxException("']' or ',' were expected, " +
                    		"found " + currentChar + " instead.");
                }
            }
            else {
                throw new SyntaxException("Was expecting ' or \", found " +
                        currentChar + "instead.");
            }
        } while (currentChar != ']');
        return indexNode;
    }
    
    /**
     * Advances the stream of one character.
     * 
     * It will set currentChar to the char that was read.
     * 
     * @return the integer value that was read on the stream.
     */
    private int getNextChar() {
        try {
            int nextChar = isr.read();
            if (nextChar == -1) eofReached = true;
                    
            currentChar = (char) nextChar;
            return nextChar;
        } catch (IOException e) {
            throw new LolIOException(e);
        }
    }
    
    /**
     * Cancels the last read, the last character will return to the stream, so
     * it can be read again. It is used in case a method wants to let another
     * method advance the stream after some specific character was found.
     */
    private void unread() {
        try {
            isr.unread(currentChar);
        } catch (IOException e) {
            throw new LolIOException(e);
        }
    }
    
  

}
