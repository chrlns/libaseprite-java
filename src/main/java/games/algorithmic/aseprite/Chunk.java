package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Chunk {
    private long length;
    
    public void read(DataInputStream in) throws IOException {        
        length = ByteTools.readInt(in);
    }
}
