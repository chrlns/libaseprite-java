package games.algorithmic.aseprite;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class FrameHeader {
    public static final int MAGIC = 0xF1FA;
    
    private long numChunks;
    private int frameDuration;
    private long frameSize;
    
    public void read(InputStream in) throws IOException {
        frameSize = ByteTools.readInt(in);
        
        int magic = ByteTools.readShort(in);
        if (magic != MAGIC) {
            throw new IOException("Invalid frame header.");
        }
        
        numChunks = ByteTools.readInt(in);
        
        frameDuration = ByteTools.readShort(in);
        
        in.skip(2); // reserved
        
        if (numChunks == 0xFFFF) {
            // Use the extended field
            numChunks = ByteTools.readInt(in);
        } else {
            in.skip(4);
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
