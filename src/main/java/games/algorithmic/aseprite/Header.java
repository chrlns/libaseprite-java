package games.algorithmic.aseprite;

import java.io.DataInputStream;
import java.io.IOException;

/**
 *
 * @author Christian Lins <christian@lins.me>
 */
class Header {
    public static final int MAGIC = 0xA5E0;
    
    private int numFrames;
    private int imageWidth, imageHeight;
    private long flags;
    
    private short paletteTransparentColorIdx;
    private int numColors;
    private short pixelWidth, pixelHeight;
    private int gridXPos, gridYPos;
    private int gridWidth;
    
    public void read(DataInputStream in) throws IOException {
        // Skip files size
        in.skipBytes(4); 
        
        int magic = ByteTools.readShort(in);
        if (magic != MAGIC) {
            throw new IOException("Invalid file format.");
        }
        
        numFrames = ByteTools.readShort(in);
        
        imageWidth  = ByteTools.readShort(in);
        imageHeight = ByteTools.readShort(in);
        
        flags = ByteTools.readInt(in);
        
        // Skip deprecated speed field
        in.skipBytes(2);
        
        // Skip 8 zero bytes
        in.skipBytes(8);
        
        paletteTransparentColorIdx = in.readByte();
        in.skipBytes(3); // Skip 3 bytes
        
        numColors = ByteTools.readShort(in);
        if (numColors == 0) {
            numColors = 256; // for old sprites
        }
        
        // Pixel ratio is width/height
        pixelWidth  = in.readByte();
        pixelHeight = in.readByte();
        
        // Grid X/Y position, really a signed short
        gridXPos = ByteTools.readShort(in); 
        gridYPos = ByteTools.readShort(in);
        
        gridWidth = ByteTools.readShort(in);
        
        // Skip 84 bytes (reserved for future)
        in.skipBytes(84);
    }
    
    public int getNumFrames() {
        return numFrames;
    }
}
