package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class FrameHeader {
    public static final int MAGIC = 0xF1FA;
    
    private long numChunks;
    private int frameDuration;
    private long frameSize;
    
    public void read(DataInputStream in) throws IOException {
        frameSize = in.readInt();
        
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
    
    public long getNumChunks() {
        return numChunks;
    }
    
    public int getFrameDuration() {
        return frameDuration;
    }
    
    public long getFrameSize() {
        return frameSize;
    }
}
