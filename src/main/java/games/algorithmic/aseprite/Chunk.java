package games.algorithmic.aseprite;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Chunk {
    private long length;
    private int type;
    
    public void read(BufferedInputStream in) throws IOException {        
        length = ByteTools.readInt(in);
        
        type = ByteTools.readShort(in);
        
        switch(type) {  
            case 0x2004:
                // read layer chunk
                break;
            case 0x0004:    // Old palette chunk
            case 0x0011:    // Old palette chunk   
            default:
                // ignore unknown type
                in.skip((int)(length - 6));
                System.out.printf("Skip chunk %x\n", type);
                break;
        }
    }
}
