package games.algorithmic.aseprite;

import java.io.InputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Chunk {
    private long length;
    private int type;
    
    public void read(InputStream in) throws IOException {        
        length = ByteTools.readInt(in);
        
        type = ByteTools.readShort(in);
        
        switch(type) {  
            case 0x2004:
                // read layer chunk
               // break;
            case 0x2005:
            case 0x2006:
            case 0x2007:
            case 0x2019:
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
