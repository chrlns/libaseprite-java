package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
public class FrameHeader {
    public static final int MAGIC = 0xF1FA;
    
    private long numChunks;
    private int frameDuration;
    
    public void read(DataInputStream in) throws IOException {
        // Skip frame size
        in.skipBytes(4);
        
        int magic = in.readShort();
        if (magic != MAGIC) {
            throw new IOException("Invalid frame header.");
        }
        
        numChunks = in.readShort();
        
        frameDuration = in.readShort();
        
        in.skipBytes(2); // reserved
        
        if (numChunks == 0xFFFF) {
            // Use the extended field
            numChunks = in.readInt();
        } else {
            in.skipBytes(4);
        }
    }
    
    public long numChunks() {
        return numChunks;
    }
}
